package github.johnnysc.watches;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
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
    List<Banner> bannerLIST;
    List<Watch> watchLIST;
    @BindString(R.string.signature_banner) String signatureBanner;
    @BindString(R.string.nonce_banner) String nonceBanner;
    @BindString(R.string.signature_watch) String signatureWatch;
    @BindString(R.string.nonce_watch) String nonceWatch;
    @BindView(R.id.infViewPager) InfiniteViewPager infViewPager;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    WatchRecycleAdapter watchAdapter;
    CustomPagerAdapter mCustomPagerAdapter;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        double viewPagerWidth = (double) infViewPager.getWidth()*1.001;
        double viewPagerHeight = viewPagerWidth * 0.61037;
        layoutParams.width = (int) viewPagerWidth;
        layoutParams.height = (int) viewPagerHeight;

        infViewPager.setLayoutParams(layoutParams);
        infViewPager.setOffscreenPageLimit(3);
        infViewPager.setAdapter(adapter);

        //makeAutoScrolling(); TODO uncomment this if you want make it auto scroll (also in InfiniteViewPager uncomment onTouch method)
    }

    private void makeAutoScrolling() {
            final Handler handler = new Handler();

            final Runnable update = new Runnable() {
                public void run() {
                    int nextItem = infViewPager.getCurrentItem();
                    infViewPager.setCurrentItem(--nextItem, true);
                }
            };

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 3000, 2000);
    }

    private void initWatchesView(){
        layoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        watchAdapter = new WatchRecycleAdapter(watchLIST);
        recyclerView.setAdapter(watchAdapter);
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
