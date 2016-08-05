package github.johnnysc.watches;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import github.johnnysc.watches.Adapters.*;
import github.johnnysc.watches.Rest.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class MainActivity extends Activity {
    RestManager restManager;
    final String signatureBanner = "9663CB5B848B7ADF7B76A39A08F62C454CE48C19E5DF18F55BC1953207646622";
    final String nonceBanner = "984";
    final String signatureWatch = "F45836929CE3A2586406C0B1F04BE37B01098841A4B62FD8E7E12DCDC86CB625";
    final String nonceWatch = "330";
    List<Banner> bannerLIST;
    List<Watch> watchLIST;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    GridView gridView;
    WatchAdapter watchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBannerData();
        getWatchesData();
    }

    private void getBannerData() {
        restManager = new RestManager();
        bannerLIST = new ArrayList<>();
        Call<BannerList> bannersList = restManager.getWatchService().getBannersList(nonceBanner, signatureBanner);
        bannersList.enqueue(new Callback<BannerList>() {
            @Override
            public void onResponse(Call<BannerList> call, Response<BannerList> response) {
                if (response.isSuccessful()) {
                    BannerList bannersList = response.body();
                    bannerLIST = bannersList.getBannersList();
                    for (Banner banner : bannerLIST) {
                       Log.d("myLog", "seems to be successful, here is the list of Banners " + banner.getImg());
                    }
                    initBannerView();
                } else
                    Log.d("myLog", "Here is the response code for banner" + response.code());
            }

            @Override
            public void onFailure(Call<BannerList> call, Throwable t) {
                Log.d("myLog", "THIS is a banner FAILURE ! " + t.getMessage());
            }
        });
    }

    private void getWatchesData() {
        watchLIST = new ArrayList<>();
        Call<WatchList> watchesList = restManager.getWatchService().getWatchesList(nonceWatch,signatureWatch);
        watchesList.enqueue(new Callback<WatchList>() {
            @Override
            public void onResponse(Call<WatchList> call, Response<WatchList> response) {
                if(response.isSuccessful()){
                    WatchList watchList = response.body();
                    watchLIST = watchList.getWatchesList();
                    for(Watch watch : watchLIST){
                        Log.d("myLog","seems to be successful, here is the list of Watches " + watch.getImg());
                    }
                    initWatchesView();
                }else
                    Log.d("myLog","Here is the response code for watch " + response.code());
            }

            @Override
            public void onFailure(Call<WatchList> call, Throwable t) {
                Log.d("myLog", "THIS is a watch FAILURE ! " + t.getMessage());
            }
        });
    }

    private void initBannerView() {
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        PagerAdapter adapter = new InfinitePagerAdapter(mCustomPagerAdapter);

        mViewPager = (ViewPager) findViewById(R.id.bannerViewPager);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        double viewPagerWidth = (double) mViewPager.getWidth()*1.001;
        double viewPagerHeight = viewPagerWidth * 0.61037;
        layoutParams.width = (int) viewPagerWidth;
        layoutParams.height = (int) viewPagerHeight;

        mViewPager.setLayoutParams(layoutParams);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
    }

    private void initWatchesView(){
        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setBackgroundColor(Color.WHITE);
        gridView.setHorizontalSpacing(0);
        gridView.setVerticalSpacing(0);
        gridView.setFriction(ViewConfiguration.getScrollFriction()*10);
        watchAdapter = new WatchAdapter(this, (ArrayList<Watch>) watchLIST);
        gridView.setAdapter(watchAdapter);
        Log.d("myLog","count of Watches is " + watchAdapter.getCount());
    }

    private class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        ImageView imageView;
        int mPosition;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return bannerLIST.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            mPosition = position;

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Picasso.with(mContext)
                    .load(bannerLIST.get(position).getImg())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.progress_animation)
                    .into(imageView);
            Log.d("myLog"," PICASSO MADE NOW ! ");

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}