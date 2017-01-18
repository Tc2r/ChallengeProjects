package com.tc2r.finbonacci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tc2r on 1/03/2016.
 *
 * Fibonacci Sequence - Enter a number and have the program generate the
 * Fibonacci sequence to that number or to the Nth number.
 *
 * Recursive, complexity is 2^n
 */

public class MainActivity extends AppCompatActivity  {

	private int aNum, bNum, cNum, count;
	public Button goButton;
	public EditText cntET;
	public TextView seqTV;
	private static final String TAG = MainActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		aNum = 1;
		bNum = 0;
		cNum = 0;
		count = 0;

		goButton = (Button) findViewById(R.id.go_btn);
		cntET = (EditText) findViewById(R.id.et_count);
		seqTV = (TextView) findViewById(R.id.tv_sequence);

		goButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userNum = cntET.getText().toString();
				seqTV.setText("");
				callFinbonacci(Integer.parseInt(userNum));
				//Log.e(TAG, "userNum is "+ userNum);
			}
		});

	}

	public void callFinbonacci(int cnt){

//        int counting = 0;
//        aNum = 1;
//        bNum = 0;
//        cNum = 0;
//        while(counting < cnt){
//            if(counting < 2){
//                aNum = 1;
//                cNum = 1;
//                bNum = aNum;
//                aNum = cNum;
//                seqTV.append(cNum +", " );
//                cNum = aNum + bNum;
//            }else{
//                cNum = aNum + bNum;
//                seqTV.append(cNum + ", ");
//                bNum = aNum;
//                aNum = cNum;
//            }
//            counting = (counting + 1);

		aNum = 0;
		bNum = 1;
		cNum = 0;
		seqTV.setText(String.valueOf(bNum));
		while(bNum < cnt){
			cNum = aNum + bNum;
			aNum = bNum;
			bNum = cNum;
			seqTV.append(", "+ cNum );
		}
		seqTV.append("!");




	}
}

