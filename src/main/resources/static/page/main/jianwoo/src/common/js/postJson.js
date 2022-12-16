import axios from 'axios'
import CONFIG from './config.js'
import {ElMessage} from 'element-plus'

export function postJson(str, p) {

    var url = location.protocol + '//' + location.host + '/api' + str;

    url = doAddUrlVersion(url);
    var instance = axios.create({

        baseURL: url,
        method: "post",
        headers: {
            'content-type': 'application/json;charset=UTF-8'
        }
    });
    // console.log(url)



    function doAddUrlVersion(url, version) {
        if (!version) {
            version = 1;
        }
        var idx = url.indexOf('?');
        if (idx === -1) {
            return url + "?v=" + version;
        }
        return url + "&v=" + version

    }

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
        method: 'post',
        url: url,
        data: p
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