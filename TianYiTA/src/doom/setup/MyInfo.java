package doom.setup;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.telephony.TelephonyManager;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.text.format.Formatter;

import java.io.*;

import com.example.tianyita.R;


public class MyInfo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_myinfo, container, false);

        TelephonyManager mTm = (TelephonyManager) this.getActivity().getSystemService(getActivity().TELEPHONY_SERVICE);
        TextView textIMEI = (TextView) rootview.findViewById(R.id.textIMEI);
        textIMEI.setText(mTm.getDeviceId());

        TextView textIMSI = (TextView) rootview.findViewById(R.id.textIMSI);
        textIMSI.setText(mTm.getSubscriberId());

        TextView textPB = (TextView) rootview.findViewById(R.id.textPB);
        textPB.setText(android.os.Build.BRAND);

        TextView textPM = (TextView) rootview.findViewById(R.id.textPM);
        textPM.setText(android.os.Build.MODEL);

        TextView textSDK = (TextView) rootview.findViewById(R.id.textSDK);
        textSDK.setText("API" + Build.VERSION.SDK);

        TextView textSys = (TextView) rootview.findViewById(R.id.textSysVerion);
        textSys.setText("Android" + Build.VERSION.RELEASE);

        TextView textCPUM = (TextView) rootview.findViewById(R.id.textCPUM);
        textCPUM.setText(getCpuInfo()[0]);

        TextView textTotalMem = (TextView) rootview.findViewById(R.id.textTotalMem);
        textTotalMem.setText(getTotalMemory());

        TextView textCueMem = (TextView) rootview.findViewById(R.id.textCurMem);
        textCueMem.setText(getAvailMemory());

        TextView textAvaInternalMem = (TextView) rootview.findViewById(R.id.textAvaInternalMem);
        textAvaInternalMem.setText(Long.toString(getAvailableInternalMemorySize()/(1024*1024)) + "M");

        TextView TotalInternalMem = (TextView) rootview.findViewById(R.id.textTotalInternalMem);
        TotalInternalMem.setText(Long.toString(getTotalInternalMemorySize()/(1024*1024)) + "M");

        TextView textAvaExternalMem = (TextView) rootview.findViewById(R.id.textAvaExternalMem);
        textAvaExternalMem.setText(Long.toString(getAvailableExternalMemorySize()/(1024*1024)) + "M");

        TextView TotalExternalMem = (TextView) rootview.findViewById(R.id.textTotalExternalMem);
        TotalExternalMem.setText(Long.toString(getTotalExternalMemorySize()/(1024*1024)) + "M");
        //记得在AndroidManidest.xml中添加权限代码！


        return rootview;
    }

    
    
    private String[] getCpuInfo()      //获取cpu信息
    {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return cpuInfo;
    }

    private String getAvailMemory()     // 获取android当前可用内存大小
    {
        ActivityManager am = (ActivityManager) this.getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(getActivity().getBaseContext(), mi.availMem);// 将获取的内存大小规格化
    }

    private String getTotalMemory()     //获得系统总内存
    {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return Formatter.formatFileSize(getActivity().getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    private long getAvailableInternalMemorySize()
    {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blocksize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks*blocksize;
    }

    private long getTotalInternalMemorySize()
    {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blocksize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks*blocksize;
    }

    private  boolean externalMemoryAvailable()
    {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private long getAvailableExternalMemorySize()
    {
        if (externalMemoryAvailable())
        {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blocksize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks*blocksize;
        }
        else
            return -1;
    }

    private long getTotalExternalMemorySize()
    {
        if (externalMemoryAvailable())
        {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blocksize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks*blocksize;
        }
        else
            return -1;
    }
}
