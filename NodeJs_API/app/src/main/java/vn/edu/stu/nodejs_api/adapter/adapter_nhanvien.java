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
import vn.edu.stu.nodejs_api.control.NhanVien;

public class adapter_nhanvien extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    List<NhanVien> objectNhanVien;

    public adapter_nhanvien( Activity context, int resource, List<NhanVien> objectNhanVien) {
        super(context, resource, objectNhanVien);
        this.context = context;
        this.resource = resource;
        this.objectNhanVien = objectNhanVien;
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
        final NhanVien nhanVien=objectNhanVien.get(position);
        makh.setText("Mã Nhân Viên: " + nhanVien.getManv());
        tenkh.setText("Tên Nhân Viên: " + nhanVien.getTennv());
        diachi.setText("Địa Chỉ Nhân Viên: " + nhanVien.getDiachinv());
        sdt.setText("Số Điện Thoại Nhân Viên: " + nhanVien.getSđt());
        email.setText("Email Nhân Viên: " + nhanVien.getEmail());
        return v;
    }
}
