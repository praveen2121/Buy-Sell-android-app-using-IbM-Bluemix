
package com.ibm.mobileappbuilder.localsell20160923170219.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.localsell20160923170219.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.localsell20160923170219.ds.ProductsDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class BuyDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<ProductsDSSchemaItem> implements ShareBehavior.ShareListener  {

    private Datasource<ProductsDSSchemaItem> datasource;
    public static BuyDetailFragment newInstance(Bundle args){
        BuyDetailFragment fr = new BuyDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public BuyDetailFragment(){
        super();
    }

    @Override
    public Datasource<ProductsDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("products"),
              URI.create("https://06810d66-5375-4fad-90b5-56a5007b6754-bluemix:b11241e1596018fcc4fe13b913aeebf106b5765f64d8c473425630675df9e3d4@06810d66-5375-4fad-90b5-56a5007b6754-bluemix.cloudant.com/products/"),
              ProductsDSSchemaItem.class,
              new SearchOptions(),
              null
      );
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.buydetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSSchemaItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = StringUtils.parseUrl(item.productimage);
        if(view0Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view0.setImageDrawable(null);
        }
        if (item.productname != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.productname);
            
        }
        if (item.salePrice != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText("Rs " + item.salePrice);
            
        }
        if (item.productlocation != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.productlocation.toString());
            bindAction(view3, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.productlocation.toString()));
        }
    }

    @Override
    protected void onShow(ProductsDSSchemaItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
    @Override
    public void onShare() {
        ProductsDSSchemaItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.productname != null ? item.productname : "" ) + "\n" +
                    (item.salePrice != null ? "Rs " + item.salePrice : "" ) + "\n" +
                    (item.productlocation != null ? item.productlocation.toString() : "" ));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

