

- The `model` is used for both *gets* and *posts* to out web page. Retrieving data and setting data to it.
The logic performed by a controller often results in some information that needs
to be carried back to the user and displayed in the browser. This information is
referred to as the `model`.
The `Model` is essentially a map (that is, a collection of key-value pairs) that will be
handed off to the view so that the data can be rendered to the client. When
`model.addAttribute("name", param);` эквивалентен тому, что если бы в сервлете писать `request.setAttribute("name",param);`

- `@ModelAttribute` - Связывание формы с объектом, что позволяет быстро и легко писать CRUD

