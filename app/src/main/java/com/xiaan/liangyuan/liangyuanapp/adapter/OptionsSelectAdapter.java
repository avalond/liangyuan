package com.xiaan.liangyuan.liangyuanapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.bean.Option;
import java.util.List;

public class OptionsSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
	private List<Option> list;
	private LayoutInflater inflater;
	private OptionsOnItemSelectedListener connectionsOnItemSelectedListener;


	public OptionsSelectAdapter(Context context, List<Option> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
	}


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.item_option_select, parent, false);
		OptionHolder holder = new OptionHolder(view);
		holder.btnOption.setOnClickListener(this);
		return holder;
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		Option option = list.get(position);
		OptionHolder optionHolder = (OptionHolder) holder;
		optionHolder.btnOption.setTag(holder);
		optionHolder.btnOption.setText(option.getName());
	}


	@Override
	public int getItemCount() {
		return list.size();
	}


	@Override
	public void onClick(View v) {
		OptionHolder holder = (OptionHolder) v.getTag();
		int position = holder.getAdapterPosition();
		if (connectionsOnItemSelectedListener != null) {
			connectionsOnItemSelectedListener.onItemSelected(position);
		}
	}


	public void setConnectionsOnItemSelectedListener(OptionsOnItemSelectedListener connectionsOnItemSelectedListener) {
		this.connectionsOnItemSelectedListener = connectionsOnItemSelectedListener;
	}


	public interface OptionsOnItemSelectedListener {
		public void onItemSelected(int position);
	}


	public class OptionHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.btn_option_select)
		Button btnOption;


		public OptionHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

}
