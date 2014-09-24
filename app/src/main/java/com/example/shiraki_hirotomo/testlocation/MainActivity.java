package com.example.shiraki_hirotomo.testlocation;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements LocationListener{
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // LocationManagerを取得
        mLocationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();

        // Accuracyを指定(低精度)
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        // PowerRequirementを指定(低消費電力)
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        // ロケーションプロバイダの取得
        String provider = mLocationManager.getBestProvider(criteria, true);

        // 取得したロケーションプロバイダを表示
        TextView tv_provider = (TextView) findViewById(R.id.provider);
        tv_provider.setText("Provider: "+provider);

        // LocationListenerを登録
        mLocationManager.requestLocationUpdates(provider, 0, 0, this);

    }

    @Override

    public void onResume() {

        super.onStart();

        // ロケーションマネージャのインスタンスを取得する
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // 位置情報の更新を受け取るように設定
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // プロバイダ
                0, // 通知のための最小時間間隔
                0, // 通知のための最小距離間隔
                this); // 位置情報リスナー

    }

    @Override

    public void onPause() {

        super.onStop();

// 位置情報の更新を止める
        mLocationManager.removeUpdates(this);

    }

    @Override
    public void onLocationChanged(Location location) {
        // 緯度の表示
        TextView tv_lat = (TextView) findViewById(R.id.latitude);
        tv_lat.setText("Latitude:"+location.getLatitude());

        // 経度の表示
        TextView tv_lng = (TextView) findViewById(R.id.longitude);
        tv_lng.setText("Longitude:"+location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
