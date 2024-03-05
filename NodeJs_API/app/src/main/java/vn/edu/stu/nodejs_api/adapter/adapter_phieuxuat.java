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
import vn.edu.stu.nodejs_api.control.PhieuNhap;
import vn.edu.stu.nodejs_api.control.PhieuXuat;

public class adapter_phieuxuat extends ArrayAdapter<PhieuXuat> {
    Activity context;
    int resource;
    List<PhieuXuat> objectPhieuXuat;

    public adapter_phieuxuat(Activity context, int resource, List<PhieuXuat> objectPhieuXuat) {
        super(context, resource, objectPhieuXuat);
        this.context = context;
        this.resource = resource;
        this.objectPhieuXuat = objectPhieuXuat;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View v=inflater.inflate(this.resource,null);
        TextView UI=v.findViewById(R.id.itemPN);


        final PhieuXuat phieuXuat=objectPhieuXuat.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("sopx: ").append(phieuXuat.getSopx()).append("\n");
        builder.append("ngaypx: ").append(phieuXuat.getNgaypx()+"").append("\n");
        builder.append("soluongpx: ").append(phieuXuat.getSoluongpx()+"").append("\n");
        builder.append("masp: ").append(phieuXuat.getMasp()+"").append("\n");
        builder.append("manv: ").append(phieuXuat.getManv()+"").append("\n");
        UI.setText(builder);

        return v;
    }
}
