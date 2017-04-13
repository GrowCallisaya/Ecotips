package com.xiberty.ecotips;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.xiberty.ecotips.fragments.CategoryFragment;
import com.xiberty.ecotips.fragments.ProductFragment;
import com.xiberty.ecotips.fragments.WebFragment;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    public enum Menues {
        HOME(1),
        PRODUCTS(2),
        CATEGORIES(3),
        ABOUT(5);

        public int id;

        private Menues(int id) {
            this.id = id;
        }

        public long getID() {
            return (long) this.id;
        }
    }

    public Drawer drawer;
    public  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar(); //Adding Toolbar & Navigation Drawer
    }

    /**
     * Establece la toolbar como action bar
     */
    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.prod7);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        //Items

        PrimaryDrawerItem item_home = new PrimaryDrawerItem().withIdentifier(1).withIcon(FontAwesome.Icon.faw_home).withName("Inicio");
        PrimaryDrawerItem item_presentation = new PrimaryDrawerItem().withIdentifier(2).withIcon(FontAwesome.Icon.faw_inbox).withName("Productos");
        PrimaryDrawerItem item_service= new PrimaryDrawerItem().withIdentifier(3).withIcon(FontAwesome.Icon.faw_building).withName("Categorias");
        PrimaryDrawerItem item_about= new PrimaryDrawerItem().withIdentifier(5).withIcon(FontAwesome.Icon.faw_info).withName("Favoritos");

        // Create the AccountHeader
        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ecotipsbanner)
                .build();

        //Now create your drawewr and pass the AccountHeader.Result
         drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(accountHeader)
                .addDrawerItems(item_home,
                        item_presentation,
                        item_service,
                        item_about)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                     @Override
                     public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                         if (drawerItem != null) {
                             if (drawerItem instanceof Nameable) {
                                 toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                             }
                             showMenu(drawerItem.getIdentifier());
                         }
                         return false;
                     }
                 })
        .build();

        drawer.setSelection(Menues.HOME.getID());
        drawer.getRecyclerView()
                .setVerticalScrollBarEnabled(false);

    }

    public void showMenu(long identifier){
        if (identifier==Menues.HOME.getID()) {
            setContainer(new ProductFragment());

        } else if(identifier==Menues.PRODUCTS.getID()){
            setContainer(new CategoryFragment());

        } else if(identifier==Menues.CATEGORIES.getID()){
            setContainer(WebFragment.newInstance("categories"));

        }else if(identifier==Menues.ABOUT.getID()){
            setContainer(WebFragment.newInstance("about"));
        }

    }

    public void setContainer(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }

}
