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

  Graph.Layout.Fixed = function(graph) {
    this.graph = graph;
    this.layout();
  };
  Graph.Layout.Fixed.prototype = {
    layout: function() {
      this.layoutPrepare();
      this.layoutCalcBounds();
    },

    layoutPrepare: function() {
      for (i in this.graph.nodes) {
        var node = this.graph.nodes[i];
        if (node.x) {
          node.layoutPosX = node.x;
        } else {
          node.layoutPosX = 0;
        }
        if (node.y) {
          node.layoutPosY = node.y;
        } else {
          node.layoutPosY = 0;
        }
      }
    },

    layoutCalcBounds: function() {
      var minx = Infinity,
        maxx = -Infinity,
        miny = Infinity,
        maxy = -Infinity;

      for (i in this.graph.nodes) {
        var x = this.graph.nodes[i].layoutPosX;
        var y = this.graph.nodes[i].layoutPosY;

        if (x > maxx) maxx = x;
        if (y > maxy) maxy = y;
        if (y < miny) miny = y;
        if (x < minx) minx = x;
      }

      this.graph.layoutMinX = minx;
      this.graph.layoutMaxX = maxx;

      this.graph.layoutMinY = miny;
      this.graph.layoutMaxY = maxy;
    }
  };

  var width = $(document).width();
  var height = $(document).height();
  graph = new Graph();

  graph.addNode('A');
  graph.addNode('B');
  graph.addNode('C');
  graph.addNode('D');

  graph.addEdge("A", "B", {
    directed: true,
  });
  graph.addEdge("B", "C", {
    directed: true,
  });

  //var layouter = new Graph.Layout.TournamentTree(g, g.nodes);
  //var layouter = new Graph.Layout.TournamentTree(graph, topologicalSort(graph));
  var layouter = new Graph.Layout.TournamentTree(graph, topologicalSort(graph));

  var renderer = new Graph.Renderer.Raphael('canvas', graph, width, height);

});
