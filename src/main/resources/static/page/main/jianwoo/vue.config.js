const {defineConfig} = require('@vue/cli-service')
const TerserPlugin = require('terser-webpack-plugin');
const path = require('path');


function resolve(dir) {
    return path.join(__dirname, '.', dir);
}

module.exports = defineConfig({
    transpileDependencies: true,
    productionSourceMap: false,
    devServer: {
        port: 8080,
        proxy: {
            '/api': {
                target: process.env.VUE_APP_API_SRV_URL,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/api'
                }
            }
        }
    },
    // 输出文件目录
    outputDir: process.env.OUTPUT_DIR,
    chainWebpack: config => {
        config.module.rule('compile')
            .test(/\.js$/)
            .include
            .add(resolve('src'))
            .add(resolve('test'))
            .add(resolve('node_modules/webpack-dev-server/client'))
            .add(resolve('node_modules'))
            .end()
            .use('babel')
            .loader('babel-loader')
            .options({
                presets: [
                    ['@babel/preset-env', {
                        modules: false
                    }]
                ]
            });
    },
    configureWebpack: config => {
        if (process.env.NODE_ENV !== 'production') return
        return {
            plugins: [
                new TerserPlugin({
                    terserOptions: {
                        ecma: undefined,
                        warnings: false,
                        parse: {},
                        compress: {
                            drop_console: true, // 清除 console 语句
                            drop_debugger: false, // 清除 debugger 语句
                            // pure_funcs: ['console.log'] // 移除console
                        }
                    },
                }),
            ]
        }
    },

})
