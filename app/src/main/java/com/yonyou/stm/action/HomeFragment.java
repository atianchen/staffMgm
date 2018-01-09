package com.yonyou.stm.action;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.yonyou.stm.R;
import com.yonyou.stm.api.req.ocr.OcrConfig;
import com.yonyou.stm.api.req.ocr.OcrImg;
import com.yonyou.stm.api.req.ocr.OcrInputs;
import com.yonyou.stm.api.req.ocr.OcrReq;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.LogService;
import com.yonyou.stm.util.FileUtils;
import com.yonyou.stm.util.HttpUtils;
import com.yonyou.stm.util.ImgUtils;
import com.yonyou.stm.util.JsonUtils;
import com.yonyou.stm.widget.BaseFragment;
import com.yonyou.stm.widget.CheckPermissionCallback;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.yonyou.stm.ctx.Constants.OCR_AppCode;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements Runnable {
    // TODO: Rename parameter arguments, choose names that match

    private File mTmpFile;

    //private Uri imageUri;

    private ImageView imgViewFront;
    private ImageView imgViewBack;
    private ImageButton btn;

    private View view;

    private OnFragmentInteractionListener mListener;

    private String idCardSide = IDCardParams.ID_CARD_SIDE_FRONT;
    private Staff staff;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        imgViewFront =  view.findViewById(R.id.idFrontView);
        imgViewBack =  view.findViewById(R.id.idBackView);
        btn = (ImageButton) view.findViewById(R.id.btn_scan);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View var1)
            {
                 capture();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void capture()
    {
        HomeFragment.this.checkPermission(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET}, new CheckPermissionCallback() {
            @Override
            public void callback(int result) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mTmpFile = FileUtils.createFile(HomeFragment.this.getContext());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(HomeFragment.this.getContext(),Constants.FILEPROVIDER, mTmpFile));
                } else {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
                }
                startActivityForResult(cameraIntent, Constants.REQUESTCODE_CAMERA);
            }
        });
    }

    private void recIDCard() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 相机拍照完成后，返回图片路径
        if (requestCode == Constants.REQUESTCODE_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                if (mTmpFile != null) {
                    btn.setClickable(false);
                    mTmpFile = ImgUtils.imageZoom(mTmpFile,1024);
                    //this.imageUri = FileUtils.getFileUri(this.getContext(),mTmpFile);
                    if(idCardSide == IDCardParams.ID_CARD_SIDE_FRONT) {
                        this.imgViewFront.setImageURI(FileUtils.getFileUri(this.getContext(), mTmpFile));
                    }else{
                        this.imgViewBack.setImageURI(FileUtils.getFileUri(this.getContext(), mTmpFile));
                    }
                   Thread thread = new Thread(HomeFragment.this);
                    thread.start();
                   //recIDCard();
                }
            } else {
                if (mTmpFile != null && mTmpFile.exists()) {
                    mTmpFile.delete();
                }
            }
        }
    }
    public void run()
    {
        IDCardParams param = new IDCardParams();
        param.setImageFile(mTmpFile);
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);

        OCR.getInstance().recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    try {
                        btn.setClickable(true);
                        if (idCardSide == IDCardParams.ID_CARD_SIDE_FRONT) {
                            //拍照传值，正背面图片base64编码过大，无法进行页面传值，暂传图片路径
                            staff = new Staff(result, mTmpFile.getAbsolutePath()/*ImgUtils.getBase64(getActivity(), HomeFragment.this.imageUri)*/);
                            idCardSide = IDCardParams.ID_CARD_SIDE_BACK;
                            Toast.makeText(getActivity(), "正面识别成功，请拍摄背面", Toast.LENGTH_SHORT).show();
                        } else {
                            idCardSide = IDCardParams.ID_CARD_SIDE_FRONT;
                            staff.setBackInfo(result, mTmpFile.getAbsolutePath()/*ImgUtils.getBase64(getActivity(), HomeFragment.this.imageUri)*/);
                            Toast.makeText(getActivity(), "背面识别成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), StaffSetActivity.class);
                            intent.putExtra(Constants.BUNDLE_KEY_STAFF, staff);
                            getActivity().startActivity(intent);
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "识别失败，请重新拍照", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(OCRError error) {
                Toast.makeText(getActivity(), "识别失败，请重新拍照", Toast.LENGTH_SHORT).show();
            }
        });
       /* String host = "http://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String method = "POST";
        String appcode = OCR_AppCode;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        OcrConfig config = new OcrConfig();
        config.setDataValue(OcrConfig.SIDE_FACE);
        OcrImg img = new OcrImg();
        img.setDataValue(ImgUtils.getCompressBase64(this.getActivity(),this.imageUri,800));
        OcrInputs input = new OcrInputs();
        input.setConfigure(config);
        input.setImage(img);
        Map<String, String> querys = new HashMap<String, String>();

        try {
            String str = JsonUtils.toJson(new OcrReq(input));
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, JsonUtils.toJson(new OcrReq(input)));
            String content = IOUtils.toString(response.getEntity().getContent(),"utf-8");
            System.out.println(content);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
