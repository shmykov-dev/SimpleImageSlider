package com.example.slider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class ImageSlider extends ViewPager {

    private Context mContext;
    private List<String> mImageUrlList;
    private Boolean crop;
    private Boolean zoom;

    public ImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setImageUrlList(List<String> imageUrlList, Boolean needCrop, Boolean needZoom) {
        mImageUrlList = imageUrlList;
        crop = needCrop;
        zoom = needZoom;
        setAdapter(new ImagePagerAdapter());
    }

    public List<String> getImageUrlList() {
        return mImageUrlList;
    }

    private class ImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageUrlList.size();
        }

        public Object instantiateItem(ViewGroup container, int position) {
            if (zoom){
                PhotoView imageView = new PhotoView(mContext);
                if (crop){
                    Glide.with(mContext)
                            .load(mImageUrlList.get(position))
                            .centerCrop()
                            .into(imageView);
                } else {
                    Glide.with(mContext)
                            .load(mImageUrlList.get(position))
                            .fitCenter()
                            .into(imageView);
                }
                container.addView(imageView);
                return imageView;
            } else {
                ImageView imageView = new ImageView(mContext);
                if (crop){
                    Glide.with(mContext)
                            .load(mImageUrlList.get(position))
                            .centerCrop()
                            .into(imageView);
                } else {
                    Glide.with(mContext)
                            .load(mImageUrlList.get(position))
                            .fitCenter()
                            .into(imageView);
                }
                container.addView(imageView);
                return imageView;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

}
