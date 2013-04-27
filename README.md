OpenEmbedded  layer for GStreamer 1.0
=====================================

This layer provides UNOFFICIAL support for the
[GStreamer 1.0 framework](http://gstreamer.freedesktop.org/) for use with
OpenEmbedded and/or Yocto.

The recipes are adapted versions of the GStreamer 0.10 recipes from OE-core.
gst-openmax was replaced with gst-omx, since gst-openmax is unmaintained and
has not been ported to 1.0 (gst-omx is its successor). gst-ffmpeg was replaced
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
* gstreamer1.0-plugins-base
* gstreamer1.0-plugins-good
* gstreamer1.0-plugins-bad
* gstreamer1.0-plugins-ugly
* gstreamer1.0-libav
* gstreamer1.0-omx


Enabling plugins with dependencies
----------------------------------

By default, in the base/good/bad/ugly recipes, only dependency-less plugins and plugins with dependencies
that are supported by OE-core (i.e. recipes for them exist in OE-core) are always enabled.

These are:
* gstreamer1.0-plugins-base : ivorbis (Tremor), ogg, theora, vorbis
* gstreamer1.0-plugins-good : cairo, flac, gdk-pixbuf, jpeg, libpng, soup, speex, taglib
* gstreamer1.0-plugins-bad : curl, eglgles
* gstreamer1.0-plugins-ugly : a52dec, lame, mad, mpeg2dec

With the X11, ALSA, and PulseAudio plugins, the situation is a bit different. They are built depending on the
contents of the DISTRO\_FEATURES value (in other words, depending on what the OE distribution supports).

The rest is disabled by default, and can be enabled by appending to the respective PACKAGECONFIG values.
For example, to enable vpx and wavpack support in gstreamer1.0-plugins-good , add to local.conf:

    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-good = "vpx wavpack"

Note that after enabling a plugin this way, it must be ensured that recipes for the plugin's dependencies
are available. In the example above, recipes for vpx and wavpack must exist. This typically means that
additional OE layers must be used (often meta-oe or meta-multimedia).

This is also how Orc support is enabled. By default, the plugins are not built with Orc, since
its recipes are not included in OE-core. So, to enable Orc in all packages, add to local.conf for example:

    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-base = "orc"
    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-good = "orc"
    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-bad = "orc"
    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-ugly = "orc"

This is a list of all configuration values for enabling additional plugins and features in the packages:

* gstreamer1.0-plugins-base
    * `orc` : enables Orc support
    * `pango` : Pango plugins
* gstreamer1.0-plugins-good
    * `orc` : enables Orc support
    * `jack` : JACK audio system plugins
    * `vpx` : plugins for en- and decoding VP8 video streams, using Google's libvpx
    * `wavpack` : WavPack plugins
* gstreamer1.0-plugins-bad
    * `orc` : enables Orc support
    * `assrender` : ASS/SSA subtitle renderer plugins
    * `faad` : AAC decoding plugins using the FAAD library
    * `libmms` : Microsoft Multimedia Stream plugins
    * `modplug` : Decoder plugins for module files (MOD/S3M/XM/IT/..) using the ModPlug library
    * `mpg123` : MPEG-1 layer 1/2/3 audio decoder plugin using the mpg123 library
    * `opus` : Opus audio decoder plugin
    * `flite` : Flite speech synthesizer plugins
    * `opencv` : OpenCV image processing plugins
* gstreamer1.0-plugins-ugly
    * `orc` : enables Orc support
    * `cdio` : Compact Disc audio plugins using libcdio
    * `dvdread` : DVD source plugins using libdvdread
    * `x264` : h.264/AVC encoder plugin using libx264
* gstreamer1.0-libav
    * `libav` : builds the package using the system's libav instead of the included one (*not recommended* unless you really know what you are doing!)
    * `lgpl` : build the package in LGPL mode (disables GPL elements)
    * `yasm` : enable support for the yasm assembler (recommended for performance)


OpenMAX IL support
------------------

The gstreamer1.0-omx package adds support for OpenMAX IL. By default, the
recipe is configured to use the bellagio OpenMAX IL implementation that ships
with OE-Core. BSP layers are encouraged to add .bbappend files which set the
`GSTREAMER_1_0_OMX_TARGET` variable to make gstreamer1.0-omx use the device
specific OpenMAX IL support. Currently, these device specific targets are
supported:

* `rpi` : Raspberry Pi OpenMAX IL implementation

If the value of `GSTREAMER_1_0_OMX_TARGET` is changed by a .bbappend file to
a device specific value, the recipe automatically sets the `PACKAGE_ARCH` of
gstreamer1.0-omx to `MACHINE_ARCH`.
