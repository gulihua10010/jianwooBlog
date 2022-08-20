var px2rem = require('postcss-px2rem')
module.exports = {
    css: {
        loaderOptions: {
            postcss: {
                plugins: [
                    // 设计稿宽度的1/10，根据原型图给出的尺寸标注来。我这里的原型图总宽度是375
                    px2rem({ remUnit: 37.5 }),
                ],
            },
        },
    },
}