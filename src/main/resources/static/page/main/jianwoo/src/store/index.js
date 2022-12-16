import {createStore} from 'vuex'

export default createStore({
    state: {
        mainOffsetTop: 0
    },
    getters: {},
    mutations: {
        changeTop(state, top) {
            state.mainOffsetTop = top;
        },
    },
    actions: {},
    modules: {}
})
