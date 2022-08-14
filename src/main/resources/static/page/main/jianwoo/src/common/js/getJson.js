import axios from 'axios'
import CONFIG from './config.js'
import {ElMessage} from "element-plus";


export function getJson(str, p, v) {

    const url = CONFIG.API_URI + '/api' + str;
    if (!v) {
        v = 1;
    }
    p.v = v;

    var instance = axios.create({
        baseURL: url,
        method: "get",
        headers: {
            'content-type': 'application/json;charset=UTF-8'
        }
    });

    var showErrMsg = function (res) {
        var msg = 'API调用出错！请稍后重试！';
        if (res.data && res.data.msg) {
            msg = res.data.msg
        }
        ElMessage({
            showClose: true,
            message: msg,
            grouping: true,
            type: 'error'
        });
    }

    return instance({
        method: 'get',
        url: url,
        params: p
    }).then((res) => {
        //return  Promise.resolve(res.data);
        // console.log(res)
        if (res.status === 200) {
            if (res.data.status === '000000') {
                return res.data;
            } else {
                showErrMsg(res);

                return CONFIG.API_FAILED
            }
        } else {
            showErrMsg(res);
        }
        return CONFIG.API_FAILED
    }).catch((res) => {
        showErrMsg(res.response);
        return CONFIG.API_FAILED
    })
}
