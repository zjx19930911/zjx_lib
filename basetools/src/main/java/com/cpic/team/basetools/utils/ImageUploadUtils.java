package com.cpic.team.basetools.utils;

import android.preference.PreferenceManager;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.cpic.team.basetools.base.BaseConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;

import static com.cpic.team.basetools.base.BaseConfig.context;
import static com.cpic.team.basetools.utils.ToastUtils.showFailedToast;


/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class ImageUploadUtils {

    private static List<String> result1;
    private static List<String> result2;
    private static OnUploadResult onUploadResult1;

    public static void uploadWithoutLuBan(List<String> pathList1, OnUploadResult onUploadResult) {
        uploadWithoutLuBan(pathList1, new ArrayList<String>(), onUploadResult);
    }

    public static void uploadWithLuBan(List<String> pathList1, OnUploadResult onUploadResult) {
        uploadWithLuBan(pathList1, new ArrayList<String>(), onUploadResult);
    }

    public static void uploadWithoutLuBan(List<String> pathList1, List<String> pathList2, OnUploadResult onUploadResult) {
        onUploadResult1 = onUploadResult;
        result1 = new ArrayList<>();
        result2 = new ArrayList<>();
        uploadOss2(-1, pathList1, pathList2);
    }

    public static void uploadWithLuBan(List<String> pathList1, List<String> pathList2, final OnUploadResult onUploadResult) {
        onUploadResult1 = onUploadResult;
        result1 = new ArrayList<>();
        result2 = new ArrayList<>();
        List<File> listFile1 = new ArrayList<>();
        for (String s : pathList1) {
            File file = new File(s);
            listFile1.add(file);
        }
        final List<File> listFile2 = new ArrayList<>();
        for (String s : pathList2) {
            File file = new File(s);
            listFile2.add(file);
        }

        Luban.compress(context, listFile1)
                .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                .setMaxHeight(1920)             // 限制图片高度
                .setMaxWidth(1080)              // 限制图片宽度
                .putGear(Luban.CUSTOM_GEAR)
                // load all images
                .launch(new OnMultiCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(final List<File> file1) {
                        if (listFile2.size() > 0) {
                            Luban.compress(context, listFile2)
                                    .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                                    .setMaxHeight(1920)             // 限制图片高度
                                    .setMaxWidth(1080)              // 限制图片宽度
                                    .putGear(Luban.CUSTOM_GEAR)
                                    // load all images
                                    .launch(new OnMultiCompressListener() {
                                        @Override
                                        public void onStart() {
                                        }

                                        @Override
                                        public void onSuccess(List<File> file2) {
                                            List<String> path_list1 = new ArrayList<>();
                                            for (File lists : file1) {
                                                path_list1.add(lists.getPath());
                                            }
                                            List<String> path_list2 = new ArrayList<>();
                                            for (File lists : file2) {
                                                path_list2.add(lists.getPath());
                                            }
                                            uploadOss2(-1, path_list1, path_list2);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            showFailedToast("图片压缩失败");
                                            onUploadResult.onError();
                                        }
                                    });

                        } else {
                            List<String> path_list1 = new ArrayList<>();
                            for (File lists : file1) {
                                path_list1.add(lists.getPath());
                            }
                            uploadOss2(-1, path_list1, new ArrayList<String>());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showFailedToast("图片压缩失败");
                        onUploadResult.onError();
                    }
                });
    }


    public static void editWithLuBan(List<String> pathList1, List<String> pathList2, final OnUploadResult onUploadResult) {
        onUploadResult1 = onUploadResult;
        pathRealList1 = pathList1;
        pathRealList2 = pathList2;
        result1 = new ArrayList<>();
        result2 = new ArrayList<>();
        uploadRealList2 = new ArrayList<>();
        uploadRealList1 = new ArrayList<>();
        List<String> real_path1 = new ArrayList<>();
        List<String> real_path2 = new ArrayList<>();
        for (String s : pathList1) {
            real_path1.add(s);
        }
        for (String s : pathList1) {
            if (s.startsWith("http")) {
                real_path1.remove(s);
            }
        }
        for (String s : pathList2) {
            real_path2.add(s);
        }
        for (String s : pathList2) {
            if (s.startsWith("http")) {
                real_path2.remove(s);
            }
        }
        List<File> listFile1 = new ArrayList<>();
        for (String s : real_path1) {
            File file = new File(s);
            listFile1.add(file);
        }
        final List<File> listFile2 = new ArrayList<>();
        for (String s : real_path2) {
            File file = new File(s);
            listFile2.add(file);
        }
        if (listFile1.size() == 0 && listFile2.size() == 0) {
            upload(new ArrayList<String>(), new ArrayList<String>());
        } else if (listFile1.size() > 0 && listFile2.size() == 0) {
            Luban.compress(context, listFile1)
                    .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                    .setMaxHeight(1920)             // 限制图片高度
                    .setMaxWidth(1080)              // 限制图片宽度
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnMultiCompressListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onSuccess(List<File> file1) {
                            Log.e("成功", "服务图片压缩成功");
                            List<String> path_list1 = new ArrayList<>();
                            for (File lists : file1) {
                                path_list1.add(lists.getPath());
                            }
//                            List<String> path_list2 = new ArrayList<>();
//                            for (File lists : file2) {
//                                path_list2.add(lists.getPath());
//                            }
                            editOss1(-1, path_list1, new ArrayList<String>());
                        }

                        @Override
                        public void onError(Throwable e) {
                            onUploadResult1.onError();
                            showFailedToast("服务图片压缩失败");
                        }
                    });

        } else if (listFile1.size() == 0 && listFile2.size() > 0) {
            Luban.compress(context, listFile2)
                    .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                    .setMaxHeight(1920)             // 限制图片高度
                    .setMaxWidth(1080)              // 限制图片宽度
                    .putGear(Luban.CUSTOM_GEAR)
                    .launch(new OnMultiCompressListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onSuccess(List<File> file2) {
                            Log.e("成功", "服务图片压缩成功");
//                            List<String> path_list1 = new ArrayList<>();
//                            for (File lists : file1) {
//                                path_list1.add(lists.getPath());
//                            }
                            List<String> path_list2 = new ArrayList<>();
                            for (File lists : file2) {
                                path_list2.add(lists.getPath());
                            }
                            editOss2(-1, new ArrayList<String>(), path_list2);
                        }

                        @Override
                        public void onError(Throwable e) {
                            onUploadResult1.onError();
                            showFailedToast("服务图片压缩失败");
                        }
                    });

        } else {
            Luban.compress(context, listFile1)
                    .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                    .setMaxHeight(1920)             // 限制图片高度
                    .setMaxWidth(1080)              // 限制图片宽度
                    .putGear(Luban.CUSTOM_GEAR)
                    // load all images
                    .launch(new OnMultiCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(final List<File> file1) {
                            Luban.compress(context, listFile2)
                                    .setMaxSize(500)                // 限制最终图片大小（单位：Kb）
                                    .setMaxHeight(1920)             // 限制图片高度
                                    .setMaxWidth(1080)              // 限制图片宽度
                                    .putGear(Luban.CUSTOM_GEAR)
                                    // load all images
                                    .launch(new OnMultiCompressListener() {
                                        @Override
                                        public void onStart() {
                                        }

                                        @Override
                                        public void onSuccess(List<File> file2) {
                                            List<String> path_list1 = new ArrayList<>();
                                            for (File lists : file1) {
                                                path_list1.add(lists.getPath());
                                            }
                                            List<String> path_list2 = new ArrayList<>();
                                            for (File lists : file2) {
                                                path_list2.add(lists.getPath());
                                            }
                                            editOss12(-1, path_list1, path_list2);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            showFailedToast("图片压缩失败");
                                            onUploadResult.onError();
                                        }
                                    });
                        }

                        @Override
                        public void onError(Throwable e) {
                            showFailedToast("图片压缩失败");
                            onUploadResult.onError();
                        }
                    });
        }
    }

    private static void uploadOss2(int i, final List<String> path_list1, final List<String> path_list2) {
        if (path_list2.size() == 0) {
            uploadOss1(-1, path_list1);
        } else {
            i++;
            final int finalI = i;
            final int size = path_list2.size();
            String endpoint = BaseConfig.URL;
            // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
            OSS oss = new OSSClient(context, endpoint, credentialProvider);
            // 构造上传请求
            String str = path_list2.get(i);
            String[] strs = str.split("[.]");
            final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
            PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
            // 异步上传时可以设置进度回调
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                }
            });


            OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                    result2.add(pic_name);
                    //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                    if (finalI == size - 1) {
                        uploadOss1(-1, path_list1);
                    } else {
                        uploadOss2(finalI, path_list1, path_list2);
                    }
                }

                @Override
                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    // 请求异常
                    onUploadResult1.onError();
                    showFailedToast("上传oss服务器失败");
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        clientExcepion.printStackTrace();
                    }
                    if (serviceException != null) {
                        // 服务异常
                        Log.e("ErrorCode", serviceException.getErrorCode());
                        Log.e("RequestId", serviceException.getRequestId());
                        Log.e("HostId", serviceException.getHostId());
                        Log.e("RawMessage", serviceException.getRawMessage());
                    }
                }
            });

        }

    }

    private static void uploadOss1(int i, final List<String> path_list1) {
        i++;
        final int finalI = i;
        final int size = path_list1.size();
        String endpoint = BaseConfig.URL;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
        OSS oss = new OSSClient(context, endpoint, credentialProvider);
        // 构造上传请求
        String str = path_list1.get(i);
        String[] strs = str.split("[.]");
        final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
        PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                result1.add(pic_name);
                //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                if (finalI == size - 1) {
                    onUploadResult1.onSuccess(result1, result2);
                } else {
                    uploadOss1(finalI, path_list1);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                onUploadResult1.onError();
                showFailedToast("上传oss服务器失败");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    private static List<String> pathRealList1;
    private static List<String> pathRealList2;
    private static List<String> uploadRealList1;
    private static List<String> uploadRealList2;


    private static void editOss12(int i, final List<String> path_list1, final List<String> path_list2) {
        i++;
        final int finalI = i;
        final int size = path_list1.size();
        String endpoint = BaseConfig.URL;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
        OSS oss = new OSSClient(context, endpoint, credentialProvider);
        // 构造上传请求
        String str = path_list1.get(i);
        String[] strs = str.split("[.]");
        final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
        PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                uploadRealList1.add(pic_name);
                //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                if (finalI == size - 1) {
                    editOss121(-1, path_list2);
                } else {
                    editOss12(finalI, path_list1, path_list2);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                onUploadResult1.onError();
                showFailedToast("上传oss服务器失败");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    private static void editOss121(int i, final List<String> path_list2) {
        i++;
        final int finalI = i;
        final int size = path_list2.size();
        String endpoint = BaseConfig.URL;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
        OSS oss = new OSSClient(context, endpoint, credentialProvider);
        // 构造上传请求
        String str = path_list2.get(i);
        String[] strs = str.split("[.]");
        final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
        PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                uploadRealList2.add(pic_name);
                //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                if (finalI == size - 1) {
                    upload(uploadRealList1, uploadRealList2);
                } else {
                    editOss121(finalI, path_list2);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                onUploadResult1.onError();
                showFailedToast("上传oss服务器失败");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    private static void editOss1(int i, final List<String> pathList1, final ArrayList<String> pathList2) {
        i++;
        final int finalI = i;
        final int size = pathList1.size();
        String endpoint = BaseConfig.URL;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
        OSS oss = new OSSClient(context, endpoint, credentialProvider);
        // 构造上传请求
        String str = pathList1.get(i);
        String[] strs = str.split("[.]");
        final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
        PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                uploadRealList1.add(pic_name);
                //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                if (finalI == size - 1) {
                    upload(uploadRealList1, new ArrayList<String>());
                } else {
                    editOss1(finalI, pathList1, pathList2);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                onUploadResult1.onError();
                showFailedToast("上传oss服务器失败");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    private static void editOss2(int i, final List<String> pathList1, final List<String> pathList2) {
        i++;
        final int finalI = i;
        final int size = pathList2.size();
        String endpoint = BaseConfig.URL;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConfig.User, BaseConfig.Key);
        OSS oss = new OSSClient(context, endpoint, credentialProvider);
        // 构造上传请求
        String str = pathList2.get(i);
        String[] strs = str.split("[.]");
        final String pic_name = PreferenceManager.getDefaultSharedPreferences(context).getString("login", "") + System.currentTimeMillis() + "." + strs[strs.length - 1];
        PutObjectRequest put = new PutObjectRequest(BaseConfig.Name, pic_name, str);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                uploadRealList2.add(pic_name);
                //  Log.e("path_gird.size", String.valueOf(grid_upload_path.size()));
                if (finalI == size - 1) {
                    upload(new ArrayList<String>(), uploadRealList2);
                } else {
                    editOss2(finalI, pathList1, pathList2);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                onUploadResult1.onError();
                showFailedToast("上传oss服务器失败");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }


    private static void upload(List<String> pathList1, List<String> pathList2) {
        List<String> copylist1 = new ArrayList<>();
        for (String ss : pathRealList1) {
            copylist1.add(ss);
        }
        for (String s : copylist1) {
            if (s.startsWith("http")) {
                s.replaceAll(BaseConfig.URL.split("//")[0] + "//" + BaseConfig.Name + "." + BaseConfig.URL.split("//")[1] + "/", "");
                result1.add(s);
            }
        }
        result1.addAll(pathList1);

        List<String> copylist2 = new ArrayList<>();
        for (String ss : pathRealList2) {
            copylist2.add(ss);
        }
        for (String s : copylist2) {
            if (s.startsWith("http")) {
                s.replaceAll(BaseConfig.URL.split("//")[0] + "//" + BaseConfig.Name + "." + BaseConfig.URL.split("//")[1] + "/", "");
                result2.add(s);
            }
        }
        result2.addAll(pathList2);
        onUploadResult1.onSuccess(result1, result2);
    }

    public interface OnUploadResult {
        void onError();

        void onSuccess(List<String> result1, List<String> result2);
    }
}
