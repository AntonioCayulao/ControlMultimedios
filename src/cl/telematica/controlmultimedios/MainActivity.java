package cl.telematica.controlmultimedios;

import java.util.List;

import cl.telematica.controlmultimedios.adapters.RssAdapter;
import cl.telematica.controlmultimedios.connections.ConnectionManager;
import cl.telematica.controlmultimedios.interfaces.DownloadListener;
import cl.telematica.controlmultimedios.models.EarthQuakeDataModel;
import cl.telematica.controlmultimedios.parsers.RssParser;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private ConnectivityManager connManager;
	private NetworkInfo netInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		@Override
		if(netInfo != null && netInfo.isConnected()){
			Intent intent = new Intent(getApplicationContext(), DownloadActivity.class);
			startActivity(intent);
			finish();
		} else {
			AlertDialog dialog = MessageFactory.getAlertDialog(MainActivity.this, new OnDialogAction() {
			
							},  getString(R.string.connectivity_error_title), 
								getString(R.string.connectivity_error), 
								getString(R.string.accept_button), 
								getString(R.string.config_button));
		
			if(dialog != null)
				dialog.show();
		}
		
		}
	
	@Override
	protected void onResume() {
		super.onResume();
		netInfo = connManager.getActiveNetworkInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.download, menu);
		return true;
	}

}
