package com.example.medic;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class ViewPagerActivity extends FragmentActivity {
	questionAdapter pageAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpagerc);

		List<Fragment> fragments = getFragments();

		pageAdapter = new questionAdapter(getSupportFragmentManager(),
				fragments);

		ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);

	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(eachQuestion.newInstance("Patientinfo"));
		fList.add(eachQuestion.newInstance("Question 1"));
		fList.add(eachQuestion.newInstance("Question 2"));

		return fList;
	}

	private class questionAdapter extends FragmentPagerAdapter {
		private List<Fragment> questions;

		public questionAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.questions = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.questions.get(position);
		}

		@Override
		public int getCount() {
			return this.questions.size();
		}
	}

}
