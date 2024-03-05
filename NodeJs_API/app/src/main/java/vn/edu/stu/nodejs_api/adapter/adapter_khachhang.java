package vn.edu.stu.nodejs_api.adapter;

import android.app.Activity;
import android.content.Context;

import android.text.SpannableStringBuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.KhachHang;

public class adapter_khachhang extends ArrayAdapter<KhachHang> {
    Activity context;
    int resource;
    List<KhachHang> objectKhachHang;

    public adapter_khachhang( Activity context, int resource, List<KhachHang> objectKhachHang) {
        super(context, resource, objectKhachHang);
        this.context = context;
        this.resource = resource;
        this.objectKhachHang = objectKhachHang;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView makh=v.findViewById(R.id.itemMa);
        TextView tenkh=v.findViewById(R.id.itemTen);
        TextView diachi=v.findViewById(R.id.itemdiachikh);
        TextView sdt=v.findViewById(R.id.itemSDT);
        TextView email=v.findViewById(R.id.itemEmail);
        final KhachHang khachHang=objectKhachHang.get(position);
        makh.setText(khachHang.getMakh());
        tenkh.setText(khachHang.getTenkh());
        diachi.setText(khachHang.getDiachikh());
        sdt.setText(khachHang.getSđt()+"");
        email.setText(khachHang.getEmail());

        return v;
    }
}
