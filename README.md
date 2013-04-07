OpenEmbedded  layer for GStreamer 1.0
=====================================

This layer provides UNOFFICIAL support for the
[GStreamer 1.0 framework](http://gstreamer.freedesktop.org/) for use with
OpenEmbedded and/or Yocto.

The recipes are adapted versions of the GStreamer 0.10 recipes from OE-core.
gst-openmax was replaced with gst-omx, since gst-openmax is unmaintained and
has not been ported to 0.10 (gst-omx is its successor). gst-ffmpeg was replaced
by gst-libav.


Dependencies
------------

* URI: git://git.openembedded.org/openembedded-core
* branch: master
* revision: HEAD


Package names and coexistence with 0.10
---------------------------------------

OpenEmbedded Core currently contains recipes for GStreamer 0.10.36 . Since 1.0
was designed to be able to coexist with 0.10 in the same system, the recipes
for GStreamer 1.0 use different names. These are:

* gstreamer1.0
* gstreamer1.0-base-plugins
* gstreamer1.0-good-plugins
* gstreamer1.0-bad-plugins
* gstreamer1.0-ugly-plugins
* gstreamer1.0-libav
* gstreamer1.0-omx


OpenMAX IL support
------------------

The gstreamer1.0-omx package adds support for OpenMax IL. By default, the
recipe is configured to use the bellagio OpenMax IL implementation that ships
with OE-Core. BSP layers are encouraged to add .bbappend files which set the
`GSTREAMER_1_0_OMX_TARGET` variable to make gstreamer1.0-omx use the device
specific OpenMax IL support. Currently, these device specific targets are
supported:

* `rpi` : Raspberry Pi OpenMax IL implementation

If the value of `GSTREAMER_1_0_OMX_TARGET` is changed by a .bbappend file,
the recipe automatically sets the `PACKAGE_ARCH` of gstreamer1.0-omx to
`MACHINE_ARCH`. In other words, the package is set to be machine specific.
