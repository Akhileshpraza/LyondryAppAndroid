package com.lyondry.lyondry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lyondry.lyondry.Activity.StoresActivity;
import com.lyondry.lyondry.Activity.MainActivity;
import com.lyondry.lyondry.Activity.OrderActivity;
import com.lyondry.lyondry.Activity.PaymentActivity;
import com.lyondry.lyondry.Activity.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BottomNavigationFragment extends Fragment implements View.OnClickListener {

    View view;
    RelativeLayout Home_rl,Search_rl,Add_rl,Payment_rl;
    TextView Tv1,Tv2,Tv3,Tv4,Tv5;
    ImageView IV1,Iv2,Iv3,Iv4;
    FloatingActionButton floting_btnn;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager();
        view =inflater.inflate(R.layout.bottom_layout,container,false);
        return view;

    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initailizeComponets();
    }

    public void initailizeComponets() {

        if (getView() == null)return;
        Home_rl = getView().findViewById(R.id.home_rl);
        Search_rl = getView().findViewById(R.id.search_rl);
        Add_rl = getView().findViewById(R.id.add_rl);
        Payment_rl = getView().findViewById(R.id.payment_rl);
        floting_btnn =getView().findViewById(R.id.floting_btn);

        Tv1 = getView().findViewById(R.id.tv1);
        Tv2 = getView().findViewById(R.id.tv2);
        Tv3 = getView().findViewById(R.id.tv3);
        Tv4 = getView().findViewById(R.id.tv4);
        Tv5 = getView().findViewById(R.id.tv_5);


        IV1 = getView().findViewById(R.id.iv_1);
        Iv2 = getView().findViewById(R.id.iv_2);
        Iv3 = getView().findViewById(R.id.iv_3);
        Iv4 = getView().findViewById(R.id.iv_4);


        Home_rl.setOnClickListener(this);
        Search_rl.setOnClickListener(this);
        Add_rl.setOnClickListener(this);
        Payment_rl.setOnClickListener(this);
        floting_btnn.setOnClickListener(this);

        setBottomNavigationView();

    }

    private void setBottomNavigationView() {

        int cyan_color = getActivity().getResources().getColor(R.color.cyan_light);
        int black_color = getActivity().getResources().getColor(R.color.bottom_menu);

        if (getActivity() !=null && getActivity() instanceof MainActivity){
            IV1.setColorFilter(cyan_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(black_color);


            Tv1.setTextColor(cyan_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(black_color);
            Tv5.setTextColor(black_color);


        }
        else if (getActivity() !=null && getActivity() instanceof OrderActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(cyan_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(black_color);



            Tv1.setTextColor(black_color);
            Tv2.setTextColor(cyan_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(black_color);
            Tv5.setTextColor(black_color);
        }
        else if (getActivity() !=null && getActivity() instanceof StoresActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(black_color);



            Tv1.setTextColor(black_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(black_color);
            Tv5.setTextColor(cyan_color);
        }
        else if (getActivity() !=null && getActivity() instanceof PaymentActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(cyan_color);
            Iv4.setColorFilter(black_color);


            Tv1.setTextColor(black_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(cyan_color);
            Tv4.setTextColor(black_color);
            Tv5.setTextColor(black_color);
        }
        else if (getActivity() !=null && getActivity() instanceof ProfileActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(cyan_color);



            Tv1.setTextColor(black_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(cyan_color);
            Tv5.setTextColor(black_color);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getChildFragmentManager();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case   R.id.home_rl:
                Intent intent =new Intent(getActivity(),MainActivity.class);
                getActivity().startActivity(intent);
                break;
            case   R.id.search_rl:
                Intent intent2 =new Intent(getActivity(),OrderActivity.class);
                getActivity().startActivity(intent2);
                break;
            case   R.id.floting_btn:
                Intent intent3 =new Intent(getActivity(), StoresActivity.class);
                getActivity().startActivity(intent3);
                break;
            case   R.id.add_rl:
                Intent intent4 =new Intent(getActivity(),PaymentActivity.class);
                getActivity().startActivity(intent4);
                break;
            case   R.id.payment_rl:
                Intent intent5 =new Intent(getActivity(),ProfileActivity.class);
                getActivity().startActivity(intent5);
                break;
        }

    }
}
