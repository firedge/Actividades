package com.fdgproject.firedge.deint_205;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Firedge on 01/12/2014.
 */
public class Adaptador extends FragmentPagerAdapter {

    private List<Fragment> lista;
    private String[]titulos = new String[]{"Extraescolar", "Complementaria"};

    public Adaptador(FragmentManager fm, List<Fragment> fragmentos) {
        super(fm);
        this.lista = fragmentos;
    }

    @Override
    public Fragment getItem(int i) {
        return this.lista.get(i);
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    //Para las pestañas
    @Override
     public CharSequence getPageTitle(int position) {
        return titulos[position];
    }

}
