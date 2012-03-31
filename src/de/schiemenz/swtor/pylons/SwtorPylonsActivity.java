/**
 *  Simple Swtor Pylons solver for Android
 *  written by F. Schiemenz
 */

package de.schiemenz.swtor.pylons;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

@SuppressWarnings("deprecation") 
public class SwtorPylonsActivity extends TabActivity  {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();
        // generate and populate Tabs
        tabHost.addTab(tabHost.newTabSpec("tabspec_1").setIndicator(getString(R.string.tab1)).setContent(R.id.tab_current));
        tabHost.addTab(tabHost.newTabSpec("tabspec_2").setIndicator(getString(R.string.tab2)).setContent(R.id.tab_target));
        tabHost.addTab(tabHost.newTabSpec("tabspec_3").setIndicator(getString(R.string.tab3)).setContent(R.id.tab_solution));
        // tab event listener
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
        	
        	public void onTabChanged(String s) {
        		
        		if(getTabHost().getCurrentTab() == 2) {
        			
            		TextView output  = (TextView) findViewById(R.id.output);
            		
            		output.setText("");
        			
        			output.append(getSolutionString(((Spinner) findViewById(R.id.r4t)).getSelectedItemPosition(),
        					      ((Spinner) findViewById(R.id.r4c)).getSelectedItemPosition()) + "\n");
        			
        			output.append(getSolutionString(((Spinner) findViewById(R.id.r3t)).getSelectedItemPosition(),
    					    	  ((Spinner) findViewById(R.id.r3c)).getSelectedItemPosition()) + "\n");
        			
        			output.append(getSolutionString(((Spinner) findViewById(R.id.r2t)).getSelectedItemPosition(),
    					    	  ((Spinner) findViewById(R.id.r2c)).getSelectedItemPosition()) + "\n");
        			
        			output.append(getSolutionString(((Spinner) findViewById(R.id.r1t)).getSelectedItemPosition(),
        						  ((Spinner) findViewById(R.id.r1c)).getSelectedItemPosition()) + "\n");
        		}
        	}
        });
    }
    /** function used to calculate the solution  */
	private String getSolutionString(int targetSymbol, int currentSymbol) {   	
		// very few cases warrant the use of a switch
    	switch(targetSymbol - currentSymbol) {
			case 1: return "1 x to the RIGHT!";
			case 2: return "2 x to the RIGHT!";
			case 3: return "3 x to the RIGHT or LEFT!";
			case 4: return "2 x to the LEFT!";
			case 5: return "1 x to the LEFT!";
			case -1: return "1 x to the LEFT!";
			case -2: return "2 x to the LEFT!";
			case -3: return "3 x to the RIGHT or LEFT!";
			case -4: return "2 x to the RIGHT!"; 
			case -5: return "1 x to the RIGHT!";
			default: return "* * *";
    	}	
    }
	/** creates the options menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    
    	//TODO menu.add(0, 0, 0, "Reset");
    	menu.add(0, 1, 0, "About");
    	menu.add(0, 2, 0, "Exit");
    	
    	return super.onCreateOptionsMenu(menu);
    }
    /** the options menu handler */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	switch (item.getItemId()) {
    	
    		case 0: {
    			TextView output  = (TextView) findViewById(R.id.output);
        		output.setText("");
    			return true;
    		}
    		
    		case 1: {
    			alert("About", "written by " + getString(R.string.app_author));	
    			return true;
    		}
    		
    		case 2: {
    			this.finish();
    			return true;
    		}
    		
    		default: {
    			return false;
    		}
    	}
    }
    /** a nicer way to use the alert box */
	private void alert(String title, String message) {
		
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", (OnClickListener) null);
		alertDialog.show();
	}
}