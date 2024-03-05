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

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.NhanVien;
import vn.edu.stu.nodejs_api.control.SanPham;

public class adapter_sanpham extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    List<SanPham> objectSanPham;

    public adapter_sanpham( Activity context, int resource, List<SanPham> objectSanPham) {
        super(context, resource, objectSanPham);
        this.context = context;
        this.resource = resource;
        this.objectSanPham = objectSanPham;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView makh=v.findViewById(R.id.itemMa);
        final SanPham sanPham=objectSanPham.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("Mã Sản Phẩm :").append(sanPham.getMasp()).append("\n");
        builder.append("Tên Sản Phẩm :").append(sanPham.getTensp()).append("\n");
        builder.append("Giá :").append(sanPham.getGiasp()+"").append("\n");
        builder.append("Số lượng :").append(String.valueOf(sanPham.getSoluong()+"")).append("\n");
        builder.append("Loại :").append(sanPham.getLoaisach()).append("\n");
        builder.append("Tên tác giả :").append(sanPham.getTentacgia()).append("\n");
        builder.append("Số Kho: ").append(sanPham.getSokho()).append("\n");


        makh.setText(builder);
        return v;
    }
}
