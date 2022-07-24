import Vue from 'vue'
Vue.filter('substr', function(v, len) {

    if (v.length > len) {
        return v.substr(0, len) + '...'
    } else {
        return v
    }
})