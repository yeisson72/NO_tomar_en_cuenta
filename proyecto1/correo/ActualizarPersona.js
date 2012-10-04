 d3.xhr2 = function(url, method, params, mime, callback) {
    var req = new XMLHttpRequest;
    if (arguments.length < 5) callback = mime, mime = null;
    else if (mime && req.overrideMimeType) req.overrideMimeType(mime);
    req.open(method, url, true);
    if (mime) req.setRequestHeader("Accept", mime);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", params.length);
    req.setRequestHeader("Connection", "close");
    req.onreadystatechange = function() {
      if (req.readyState === 4) {
        var s = req.status;
        callback(s >= 200 && s < 300 || s === 304 ? req : null);
      }
    };
    req.send(params);
  };

  function ActualizarPersona() {
    var cedula = d3.select("#cedula").property("value");
    var nombre = d3.select("#nombre").property("value");
    var calle = d3.select("#telefono").property("value");
    var opcervacion = d3.select("#opcervacion").property("value");
    var tipo = d3.select("#tipo").property("value");
    var params = "cedula="+cedula+"&nombre="+nombre+"&calle="+calle+
                 "&opcervacion="+opcervacion+"&tipo="+tipo;
    d3.xhr2("http://localhost:8089/rest/personas/"+cedula,"PUT",params,function(json) {
    });
    ListadoPersonas();
  }