<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Currency Converter Results</title>
    <link rel="stylesheet" href="/bulma.min.css">
    <style>
        .header {
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: 100px min-content;
        }

        .header * {
            align-self: center;
            justify-self: center;
        }

        .grid {
            display: grid;
            grid-template-rows: 75px 50px;
            grid-template-columns: 50% 50%;
        }

        .grid * {
            align-self: center;
            justify-self: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1 class="title is-3">Currency Results</h1>
        <div class="field has-addons">
            <p class="control">
                <a href="/" class="button is-small">Convert Again</a>
            </p>
            <p class="control">
                <a href="/history" class="button is-small">Previous Conversions</a>
            </p>
        </div>
    </div>
    <br>
    <div class="grid">
        <div><h4 class="title is-4"><c:out value="${result.fromCurrency}"></c:out></h4></div>
        <div><h4 class="title is-4"><c:out value="${result.toCurrency}"></c:out></h4></div>
        <div><h2 class="title is-2"><c:out value="${result.fromAmt}"></c:out></h2></div>
        <div><h2 class="title is-2"><c:out value="${result.toAmt}"></c:out></h2></div>
    </div>
</body>
</html>