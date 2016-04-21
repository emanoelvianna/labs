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

$(document).ready(function() {

  var width = $(document).width();
  var height = $(document).height();
  graph = new Graph();

  graph.addNode('Questão A', {
    x: 0,
    y: 0
  });
  graph.addNode('Questão B', {
    x: 100,
    y: 200
  });
  graph.addNode('Questão C', {
    x: 10,
    y: 0
  });
  graph.addNode('Questão D', {
    x: 150,
    y: 150
  });
  graph.addNode('Questão E', {
    x: 0,
    y: 100
  });
  graph.addNode('Questão F', {
    x: 200,
    y: 0
  });

  graph.addEdge("Questão A", "Questão B", {
    directed: true,
    label: 'vai para'
  });
  graph.addEdge("Questão B", "Questão C", {
    directed: true,
    label: 'vai para'
  });
  graph.addEdge("Questão C", "Questão D", {
    directed: true,
    label: 'vai para'
  });

  //var layouter = new Graph.Layout.TournamentTree(g, g.nodes);
  //var layouter = new Graph.Layout.TournamentTree(graph, topologicalSort(graph));
  //var layouter = new Graph.Layout.TournamentTree(graph, topologicalSort(graph));
  var layouter = Graph.Layout.Fixed(graph);
  var renderer = new Graph.Renderer.Raphael('canvas', graph, width, height);
  layouter.layout();
  renderer.draw();

});
