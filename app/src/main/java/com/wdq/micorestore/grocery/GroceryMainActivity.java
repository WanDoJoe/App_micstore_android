package com.wdq.micorestore.grocery;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.zxing.client.android.CaptureActivity;
import com.alibaba.fastjson.JSON;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.wdq.micorestore.R;
import com.wdq.micorestore.common.Common;
import com.wdq.micorestore.grocery.adapter.GroceryMainOrderAdapter;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.bean.GroceryReckoning;
import com.wdq.micorestore.grocery.dao.GroceryGoodsDaoUtils;
import com.wdq.micorestore.utils.DateUtil;
import com.wdq.micorestore.utils.PayUtils;

import java.util.ArrayList;
import java.util.List;

public class GroceryMainActivity extends AppCompatActivity {

    private static final String TAG = GroceryMainActivity.class.getSimpleName();
    private Context mContext;
    private BeepManager beepManager;

    private ImageButton setting_IBn;
    private RecyclerView grocery_main_recyclerview;
    private TextView totle_tv;
    private Button reckoning_bn;


    private Dialog payDialog;
    private View payDialog_view;

   //zxing扫描
//    private CaptureManager capture;
    private DecoratedBarcodeView bv_barcode;


    private int totlePrice_Int=0;
    private GroceryGoodsDaoUtils groceryGoodsDaoUtils;
    private  List<GroceryGoods> groceryGoodsList=new ArrayList<>();
    private List<GroceryReckoning> mGroceryReckoningList=new ArrayList<>();
    private GroceryMainOrderAdapter mGroceryMainOrderAdapter =new GroceryMainOrderAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_grocery_main);

        bv_barcode = findViewById(R.id.bv_barcode);
        bv_barcode.decodeContinuous(barcodeCallback);

        beepManager = new BeepManager(this);
        initView();
        onClick();
    }
    private void initView(){
        setting_IBn=findViewById(R.id.grocery_main_header_setting_bn);
        totle_tv=findViewById(R.id.grocery_main_bottom_order_price_totle);
        reckoning_bn=findViewById(R.id.grocery_main_bottom_reckoning_bn);

        grocery_main_recyclerview=findViewById(R.id.grocery_main_launcher_bottom_recyclerview);
        grocery_main_recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        groceryGoodsDaoUtils=new GroceryGoodsDaoUtils(mContext);
    };
    private void onClick(){
        setting_IBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,GrocerySettingActivity.class);
                startActivity(intent);
            }
        });
        reckoning_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
//                startActivity(intent);
                if(totlePrice_Int!=0) {
                    payDialog();
                }

            }
        });
    }


    //Zxing扫描监听回调
    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            bv_barcode.pause();
            if (result != null){
                Log.e(getClass().getName(), "获取到的扫描结果是：" + result.getText());
//                Toast.makeText(mContext,"获取到的扫描结果是：" + result.getText(),Toast.LENGTH_SHORT).show();
                //可以对result进行一些判断，比如说扫描结果不符合就再进行一次扫描
                queryGoods(result.getText());
                beepManager.playBeepSoundAndVibrate();
                if (result.getText().contains("符合我的结果")){
                    //符合的可以不在扫描了，当然你想继续扫描也是可以的
                    queryGoods(result.getText());
                } else {

                    bv_barcode.resume();
                }
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        bv_barcode.resume();
        beepManager.updatePrefs();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bv_barcode.pause();
        beepManager.close();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return bv_barcode.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Common.REQUEST_CODE){
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
            }
        }
    }


    private void queryGoods(String cqCode){
            try {
                String[] str = {cqCode};
                groceryGoodsList = groceryGoodsDaoUtils.queryMeiziByNativeSql("where grocery_cqcode=?", str);
                if (groceryGoodsList.isEmpty()) {
                    Toast.makeText(mContext, "错误0：该商品未录入库！！！", Toast.LENGTH_SHORT).show();
                } else {
                    GroceryGoods groceryGoods = groceryGoodsList.get(0);
                    if(mGroceryReckoningList.size()>0){
                            if(returnGroceryReckoningList(cqCode)==null){
                                totlePrice_Int+=Integer.valueOf(groceryGoods.getPrice());
                                mGroceryReckoningList.add(setGroceryReckoning(groceryGoods,
                                        "1",groceryGoods.getPrice()));
                        }
                    }else {
                        Log.e(TAG,"mGroceryReckoningList.size()==0");
                        totlePrice_Int+=Integer.valueOf(groceryGoods.getPrice());
                        mGroceryReckoningList.add(setGroceryReckoning(groceryGoods,
                                "1",groceryGoods.getPrice()));
                    }

                    totle_tv.setText("总价："+totlePrice_Int);
                    mGroceryMainOrderAdapter = new
                            GroceryMainOrderAdapter(mContext, mGroceryReckoningList,
                            R.layout.activity_grocery_main_rv_list_item);
                    grocery_main_recyclerview.setAdapter(mGroceryMainOrderAdapter);
                    mGroceryMainOrderAdapter.notifyDataSetChanged();


//        if(mGroceryMainOrderAdapter!=null){
                    mGroceryMainOrderAdapter.setmAddItemClickListener(new GroceryMainOrderAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.e(TAG,"setmAddItemClickListener");
//                    mGroceryReckoningList.set(position,  minusAndAddRockoingNumb(mGroceryReckoningList.get(position),1));
                            String numb_str=   mGroceryReckoningList.get(position).getReckoning_numb();
                            String price_str= mGroceryReckoningList.get(position).getPrice();
                            int numb_int=Integer.valueOf(numb_str);
                            int numb_=numb_int+1;
                            mGroceryReckoningList.get(position).setReckoning_numb(String.valueOf(numb_));
                            totlePrice_Int+=Integer.valueOf(price_str);
                            totle_tv.setText("总价："+totlePrice_Int);
                            mGroceryMainOrderAdapter.notifyDataSetChanged();
                        }
                    });
            mGroceryMainOrderAdapter.setmMinusItemClickListener(new GroceryMainOrderAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.e(TAG,"setmMinusItemClickListener");
//                    mGroceryReckoningList.set(position,  minusAndAddRockoingNumb(mGroceryReckoningList.get(position),-1));
                    String numb_str=   mGroceryReckoningList.get(position).getReckoning_numb();
                    String price_str= mGroceryReckoningList.get(position).getPrice();
                    int numb_int=Integer.valueOf(numb_str);
                    if(numb_int-1==0){
                        mGroceryReckoningList.remove(position);
                        totlePrice_Int-=Integer.valueOf(price_str);
                        totle_tv.setText("总价："+totlePrice_Int);
                    }else{
                        int numb_=numb_int-1;
                        totlePrice_Int-=Integer.valueOf(price_str);
                        totle_tv.setText("总价："+totlePrice_Int);
                        mGroceryReckoningList.get(position).setReckoning_numb(String.valueOf(numb_));
                    }

                    mGroceryMainOrderAdapter.notifyDataSetChanged();

                }
            });
        }
//                }
            }catch (Exception e){
                Log.e(TAG,e.getLocalizedMessage());
                Toast.makeText(mContext, "错误0：该商品未录入库！！！", Toast.LENGTH_SHORT).show();
            }
    }

    private GroceryReckoning setGroceryReckoning(GroceryGoods groceryGoods,String reckoning_numb,
                                                 String reckoning_totle_prices){
        GroceryReckoning reckoning = new GroceryReckoning(
                groceryGoods.getId(),
                groceryGoods.getGrocery_cqcode(),
                groceryGoods.getGrocery_code(),
                groceryGoods.getGrocery_goods_name(),
                groceryGoods.getGrocery_goods_brand(),
                groceryGoods.getGrocery_goods_type(),
                groceryGoods.getGrocery_goods_size(),
                groceryGoods.getGrocery_goods_date(),
                groceryGoods.getGrocery_in_date(),
                groceryGoods.getGrocery_in_numb(),
                groceryGoods.getCost_price(),
                groceryGoods.getPrice(),
                groceryGoods.getGrocery_up_sale(),
                groceryGoods.getGrocery_down_sale(),
                groceryGoods.getGrocery_remark(),
                DateUtil.geDate(),
                reckoning_numb,
                "",
                reckoning_totle_prices

        );
        return reckoning;
    }

    private GroceryReckoning returnGroceryReckoningList(String cqCode) {
        GroceryReckoning reckoning = null;
        for (int i = 0; i < mGroceryReckoningList.size(); i++) {
            Log.e(TAG, mGroceryReckoningList.size() + "==" + i);
            Log.e(TAG, mGroceryReckoningList.get(i).getGrocery_cqcode() +
                    "reckoning=numb1=" + cqCode);
            if (mGroceryReckoningList.get(i).getGrocery_cqcode().equals(cqCode)
                    && i < mGroceryReckoningList.size()) {
                reckoning = mGroceryReckoningList.get(i);
                int reckoning_numbInt = Integer.valueOf(reckoning.getReckoning_numb()) + 1;
                int reckoning_totle_priceInt1 = Integer.valueOf(reckoning.getPrice()) ;
//                        +Integer.valueOf(reckoning.getReckoning_totle_prices())
                Log.e(TAG, "reckoning=numb1=" + reckoning.getReckoning_numb());
                Log.e(TAG, "reckoning=totle1=" + reckoning.getReckoning_totle_prices());
                reckoning.setReckoning_numb(String.valueOf(reckoning_numbInt));
                reckoning.setReckoning_totle_prices(String.valueOf(reckoning_totle_priceInt1));
                Log.e(TAG, "reckoning=numb2=" + reckoning.getReckoning_numb());
                Log.e(TAG, "reckoning=totle2=" + reckoning.getReckoning_totle_prices());
                totlePrice_Int += Integer.valueOf(reckoning.getPrice());
                mGroceryReckoningList.set(i, reckoning);
                break;
            }
        }
            return reckoning;

    }
    private GroceryReckoning minusAndAddRockoingNumb(GroceryReckoning reckoning,int flag){
        int numb=Integer.valueOf(reckoning.getReckoning_numb())+flag;
        reckoning.setReckoning_totle_prices(String.valueOf(numb));
        return reckoning;
    }



    private void payDialog(){
        payDialog_view= LayoutInflater.from(mContext).inflate(R.layout.activity_grocery_main_pay_dialog_layout,null);
        payDialog= new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle("选择支付类型")
                .setView(payDialog_view)
                .setPositiveButton("支付成功", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        payDialog.dismiss();
                    }
                })
                .setNegativeButton("取消支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        payDialog.dismiss();
                    }
                })
                .show();


        Button pay_weixin=payDialog_view.findViewById(R.id.grocery_main_pay_dialog_weixin);
        Button pay_zhifubao=payDialog_view.findViewById(R.id.grocery_main_pay_dialog_zhifubao);
        Button pay_cash=payDialog_view.findViewById(R.id.grocery_main_pay_dialog_cash);

        pay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PayUtils.hasInstalledWeiXinClient(mContext)) {
                    PayUtils.startIntentUrl(GroceryMainActivity.this, PayUtils.WEIXIN_PACKAGE_NAME);
                }else{
                    Toast.makeText(mContext,"请安装微信",Toast.LENGTH_SHORT).show();
                }
            }
        });
        pay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PayUtils.hasInstalledAlipayClient(mContext)) {
                    PayUtils.startIntentUrl(GroceryMainActivity.this, PayUtils.ALIPAY_PACKAGE_NAME);
                }else{
                    Toast.makeText(mContext,"请安装支付宝",Toast.LENGTH_SHORT).show();
                }
            }
        });
        pay_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

}
