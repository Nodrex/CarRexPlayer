package com.nodrex.carrexplayer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

	private MainActivity activity;
	private List<String> data = new ArrayList<String>(); 
	
	public MusicAdapter(MainActivity activity,List<String> data) {
		this.activity = activity;
		this.data = data;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

		TextView musicName;
		
		public ViewHolder(View view) {
			super(view);
			view.setOnClickListener(this);
			musicName = (TextView) view.findViewById(R.id.musicName);
		}

		public void onClick(View v) {
			activity.play(getAdapterPosition());
		}
	}

	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music, parent, false);
		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		String fullName = data.get(position);
		String[] split = fullName.split("/");
		String name =  split[split.length-1];
		viewHolder.musicName.setText(name);
		if(position == MainActivity.getCurrentMusicINdex()){
			viewHolder.itemView.setBackgroundResource(R.color.currentMusic);
		}else{
			viewHolder.itemView.setBackgroundResource(R.color.music);
		}
	}
	
	public int getItemCount() {
		return data.size();
	}
	
}
