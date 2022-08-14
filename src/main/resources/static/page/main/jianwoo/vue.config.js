const { defineConfig } = require('@vue/cli-service')

const path = require('path');
function resolve(dir) {
    return path.join(__dirname, '.', dir);
}

module.exports = defineConfig({
  transpileDependencies: true,
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
    }

})
