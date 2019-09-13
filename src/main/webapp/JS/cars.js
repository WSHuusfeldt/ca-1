window.onload = function() {


    document.getElementById("output").addEventListener("load", start("/jpareststarter/api/cars/all"));
    
    function start(url) {
        fetch(url)
            .then(res => res.json())
            .then(data => {
                document.getElementById("output").innerHTML =
                data.map(x =>
                    "<tr><td>" + x.id + '</td>' +
                    "<td>" + x.year + '</td>' +
                    "<td>" + x.make + '</td>' +
                    "<td>" + x.model + '</td>' +
                    "<td>" + x.color + '</td>' +
                    "<td>" + x.price + '</td>' +
                    "<td>" + x.owner + '</td></tr>')
            })
    }
}