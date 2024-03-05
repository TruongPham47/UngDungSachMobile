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
import vn.edu.stu.nodejs_api.control.PhieuNhap;

public class adapter_phieunhap extends ArrayAdapter<PhieuNhap> {
    Activity context;
    int resource;
    List<PhieuNhap> objectPhieuNhap;
    public adapter_phieunhap(Activity context, int resource, List<PhieuNhap> objectPhieuNhap) {
        super(context, resource, objectPhieuNhap);
        this.context = context;
        this.resource = resource;
        this.objectPhieuNhap = objectPhieuNhap;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView UI=v.findViewById(R.id.itemPN);


        final PhieuNhap phieuNhap=objectPhieuNhap.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("sopn: ").append(phieuNhap.getSopn()).append("\n");
        builder.append("ngaypn: ").append(phieuNhap.getNgaypn()+"").append("\n");
        builder.append("soluongpn: ").append(phieuNhap.getSoluongpn()+"").append("\n");
        builder.append("masp: ").append(phieuNhap.getMasp()+"").append("\n");
        builder.append("manv: ").append(phieuNhap.getManv()+"").append("\n");
        UI.setText(builder);

        return v;
    }
}
