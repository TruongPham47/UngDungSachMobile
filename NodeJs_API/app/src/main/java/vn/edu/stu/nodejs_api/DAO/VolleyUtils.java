package vn.edu.stu.nodejs_api.DAO;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyUtils {
    private RequestQueue requestQueue;
    private String BASE_URL ;

    public VolleyUtils(Context context, String url) {
        this.BASE_URL = url;
        requestQueue = Volley.newRequestQueue(context);
    }
    public void makeGetRequestWithToken(String endpoint, final String authToken, final VolleyResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + endpoint,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Add Authorization header with the token
                headers.put("Authorization", "Bearer " + authToken);
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }
    public void makePostRequestWithToken(String endpoint, final String jsonBody, final String authToken, final VolleyResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + endpoint,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return jsonBody == null ? null : jsonBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            jsonBody, "utf-8");
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Add Authorization header with the token
                headers.put("Authorization", "Bearer " + authToken);
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void makeGetRequest(String endpoint, final VolleyResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + endpoint,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }

    public void makeGetRequest(String endpoint, Map<String, String> params, final VolleyResponseListener<String> listener) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL + endpoint);

        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlBuilder.toString(),
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }
//getmapJson
public void makegetRequest(String endpoint, final Map<String, String> params, final VolleyResponseListener<String> listener) {
    JSONObject jsonParams = new JSONObject(params);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + endpoint,
            response -> {
                if (listener != null) {
                    listener.onResponse(response);
                }
            },
            error -> {
                if (listener != null) {
                    listener.onError(error.toString());
                }
            }) {
        @Override
        public String getBodyContentType() {
            return "application/json; charset=utf-8";
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            try {
                return jsonParams == null ? null : jsonParams.toString().getBytes("utf-8");
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                        jsonParams, "utf-8");
                return null;
            }
        }
    };

    requestQueue.add(stringRequest);
}

//token
public void makegetRequesttoken(String endpoint, final Map<String, String> params, final String authToken, final VolleyResponseListener<String> listener) {
    JSONObject jsonParams = new JSONObject(params);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + endpoint,
            response -> {
                if (listener != null) {
                    listener.onResponse(response);
                }
            },
            error -> {
                if (listener != null) {
                    listener.onError(error.toString());
                }
            }) {
        @Override
        public String getBodyContentType() {
            return "application/json; charset=utf-8";
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            try {
                return jsonParams == null ? null : jsonParams.toString().getBytes("utf-8");
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                        jsonParams, "utf-8");
                return null;
            }
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<>();
            // Add Authorization header with the token
            headers.put("Authorization", "Bearer " + authToken);
            return headers;
        }
    };

    // Add the request to the RequestQueue
    requestQueue.add(stringRequest);
   // Volley.newRequestQueue(VolleyUtils.this).add(stringRequest);
}

    public void makePostRequest(String endpoint, final Map<String, String> params, final VolleyResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + endpoint,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
    //jsonPost
    public void makePostRequest(String endpoint, final String jsonBody, final VolleyResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + endpoint,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return jsonBody == null ? null : jsonBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            jsonBody, "utf-8");
                    return null;
                }
            }
        };

        requestQueue.add(stringRequest);
    }

    public void makeJsonRequest(String endpoint, JSONObject requestBody, final VolleyResponseListener<JSONObject> listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + endpoint, requestBody,
                response -> {
                    if (listener != null) {
                        listener.onResponse(response);
                    }
                },
                error -> {
                    if (listener != null) {
                        listener.onError(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    public interface VolleyResponseListener<T> {
        void onResponse(T response);
        void onError(String errorMessage);
    }
}
