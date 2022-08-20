var plugins = [
    ['prismjs', {
        'languages': ['javascript', 'css', 'markup', 'python','php', 'java', 'c', 'csharp', 'cpp','bash'],
        'plugins': ['line-numbers', 'copy-to-clipboard'],
        'theme': 'tomorrow',
        'css': true
    }]
]
if (['production', 'prod'].includes(process.env.NODE_ENV)) {
    // plugins.push("transform-remove-console")
}
module.exports = {
    presets: [
        [
            '@vue/cli-plugin-babel/preset',
            {
                "useBuiltIns": "entry",
                polyfills: [
                    'es6.promise',
                    'es6.symbol'
                ]
            }
        ],
    ],
    plugins: plugins

}
