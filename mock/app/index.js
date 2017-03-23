// var ServerMock = require("mock-http-server");
//
// // Run an HTTP server on localhost:8888
// var server = new ServerMock({host: "0.0.0.0", port: 9000});
//
// var done = function () {
//     console.log("Mock Started")
// }
//
// server.start(done);
//
// server.on({
//     method: 'GET',
//     path: '/customers/{id}',
//     delay: 500,
//     reply: {
//         status: 200,
//         headers: {"content-type": "application/json"},
//         body: JSON.stringify({hello: "world" + id}),
//     }
// });

var server = require("dyson-generators")
module.exports = {
    path: '/user/:id',
    method: 'GET',
    template: {
        id: (params, query, body) =>params.id,
    name: g.name,
    address: {
        zip: g.zipUS,
        city: g.city
    }
}
}
