if (typeof (movie) == 'undefined') { movie = {}; }
if (typeof (movie.app) == 'undefined') { movie.app = {}; }
if (typeof (movie.app.project) == 'undefined') { movie.app.project = {}; }
if (typeof (movie.app.project.api) == 'undefined') { movie.app.project.api = {}; }


(function (_context_) {

    let responseCallBack = function (response) {
        response = response || {}
        response.data = response.data || {};

        response.data.header = response.data.header || {};
        response.data.header.message = response.data.header.message || '';
        response.data.header.result = response.data.header.result || false;
        if (response.data.header.result !== true) {
            response.data.header.result = false;
        }

        console.log(response);
        return response;
    };

    let postFunction = function (url, parameter, callback) {
        callback = callback || (function () {
        });

        axios.post(url, parameter).then(function (response) {
            response = responseCallBack(response);
            callback(response.data.header.result, response.data.header.message, response.data.body.data);
        }).catch(function (err) {
            console.log(err);
        })
    };

    _context_.post = function(url, param, callback) {
        postFunction(url, param, callback);
    };


})(movie.app.project.api);


if (typeof (API_CALL) == 'undefined') { API_CALL = {}; }
API_CALL = movie.app.project.api;