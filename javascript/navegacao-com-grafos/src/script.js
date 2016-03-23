var p = Graph.Renderer.Raphael.prototype;

Graph.Renderer.Raphael = function(element, graph, width, height) {
  this.width = width;
  this.height = height;
  var selfRef = this;
  this.r = Raphael(element, this.width, this.height);
  this.radius = 40; /* max dimension of a node */
  this.graph = graph;
  this.mouse_in = false;
  this.draw();
};
// Put it back ;)
Graph.Renderer.Raphael.prototype = p;

var render = function(r, n) {
  /* the Raphael set is obligatory, containing all you want to display */
  var set = r.set().push(
      /* custom objects go here */
      r.rect(n.point[0] - 30, n.point[1] - 13, 50, 50)
      .attr({
        "fill": "#fa8",
        "stroke-width": 2,
        r: 9
      }))
    .push(r.text(n.point[0], n.point[1] + 15, n.id)
      .attr({
        "font-size": "20px"
      }));
  return set;
};

$(document).ready(function() {

  Graph.Renderer.defaultRenderFunc = render;

  var width = $(document).width();
  var height = $(document).height();
  g = new Graph();
  g.addEdge("A", "B", {
    directed: true
  });
  g.addEdge("B", "C", {
    directed: true
  });


  var layouter = new Graph.Layout.Ordered(g, topologicalSort(g));
  var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
  layouter.layout();
  renderer.draw();
});
