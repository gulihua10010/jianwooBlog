const prerender = require('prerender');
const server = prerender();
server.use(prerender.httpHeaders());

server.start();