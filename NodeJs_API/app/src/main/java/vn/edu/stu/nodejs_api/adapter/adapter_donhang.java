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
import vn.edu.stu.nodejs_api.control.DonHang;
import vn.edu.stu.nodejs_api.control.KhachHang;

public class adapter_donhang extends ArrayAdapter<DonHang> {
    Activity context;
    int resource;
    List<DonHang> objectDonHang;
    public adapter_donhang(Activity context, int resource, List<DonHang> objectDonHang) {
        super(context, resource, objectDonHang);
        this.context = context;
        this.resource = resource;
        this.objectDonHang = objectDonHang;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView UI=v.findViewById(R.id.itemMa);


        final DonHang donHang=objectDonHang.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("madh: ").append(donHang.getMadh()).append("\n");
        builder.append("ngày đặt: ").append(donHang.getNgaydathang()+"").append("\n");
        builder.append("ngày giao: ").append(donHang.getNgaygiaohang()+"").append("\n");
        builder.append("sl : ").append(donHang.getSoluong()+"").append("\n");
        builder.append("tổng : ").append(donHang.getTongtien()+"").append("\n");
        builder.append("makh : ").append(donHang.getMakh()+"").append("\n");
        builder.append("masp : " ).append(donHang.getMasp()+"").append("\n");

        UI.setText(builder);

        return v;
    }
}
