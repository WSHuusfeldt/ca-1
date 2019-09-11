window.onload = function () {
    
    document.getElementById("output").addEventListener("load", start("/jpareststarter/api/jokes/all"));
    
    
    function start(url) {
        fetch(url)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {
                    document.getElementById("output").innerHTML =
                            data.map(x =>
                                "<tr><td>" + x.joke + '</td>' +
                                        "<td>" + x.type + '</td></tr>')
                })
    }
}
    


