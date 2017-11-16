package com.xiaan.liangyuan.liangyuanapp.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.Utils.Utils;
import com.xiaan.liangyuan.liangyuanapp.adapter.OptionsSelectAdapter;
import com.xiaan.liangyuan.liangyuanapp.bean.Option;
import java.util.List;

public class OptionsMenu extends LinearLayout {
	private static final int CONTEXT_MENU_WIDTH = Utils.dpToPx(200);

	RecyclerView rv_options;

	private List<Option> list;
	private OptionsSelectAdapter.OptionsOnItemSelectedListener optionsOnItemSelectedListener;


	public OptionsMenu(Context context, List<Option> list, OptionsSelectAdapter.OptionsOnItemSelectedListener optionsOnItemSelectedListener) {
		super(context);
		this.list = list;
		this.optionsOnItemSelectedListener = optionsOnItemSelectedListener;
		init();
	}


	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.options_menu, this, true);
		setBackgroundResource(R.drawable.bg_container_shadow);
		setOrientation(VERTICAL);
		int height;
		if (list.size() == 2) {
			height = Utils.dpToPx(61 * list.size());
		} else {
			height = Utils.dpToPx(55 * list.size());
		}
		setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, height));
	}


	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		rv_options = findViewById(R.id.rv_options);
		initRecycleView();
	}


	private void initRecycleView() {
		OptionsSelectAdapter adapter = new OptionsSelectAdapter(getContext(), list);
		adapter.setConnectionsOnItemSelectedListener(optionsOnItemSelectedListener);
		LinearLayoutManager llm = new LinearLayoutManager(getContext());
		rv_options.setLayoutManager(llm);
		rv_options.setAdapter(adapter);
	}


	public void dismiss() {
		((ViewGroup) getParent()).removeView(OptionsMenu.this);
	}

}
