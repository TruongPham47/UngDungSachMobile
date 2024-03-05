package vn.edu.stu.nodejs_api.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.KhachHang;
import vn.edu.stu.nodejs_api.control.KhoHang;

public class adapter_khohang extends ArrayAdapter<KhoHang> {
    Activity context;
    int resource;
    List<KhoHang> objectKhoHang;


    public adapter_khohang( Activity context, int resource, List<KhoHang> objectKhoHang) {
        super(context, resource, objectKhoHang);
        this.context = context;
        this.resource = resource;
        this.objectKhoHang = objectKhoHang;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView UI=v.findViewById(R.id.itemWH);
        final KhoHang khoHang=objectKhoHang.get(position);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("số kho: ").append(khoHang.getSokho()).append("\n");
        builder.append("Tổng sl: ").append(khoHang.getTong()+"").append("\n");
        builder.append("sl nhập: ").append(khoHang.getNhap()+"").append("\n");
        builder.append("sl xuất :").append(khoHang.getXuat()+"").append("\n");

        UI.setText(builder);
        return v;
    }
}
