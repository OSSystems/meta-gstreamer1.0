OpenEmbedded  layer for GStreamer 1.0
=====================================

This layer provides UNOFFICIAL support for the
[GStreamer 1.0 framework](http://gstreamer.freedesktop.org/) for use with
OpenEmbedded and/or Yocto. It is used for GStreamer recipe backports, to
provide older OE versions with support for newer GStreamer versions, and
also as a staging ground for recent recipe upgrades which haven't yet made
it into OE-core (for example, because a new GStreamer version was released
just recently).


Dependencies
------------

* URI: git://git.openembedded.org/openembedded-core
* branch: morty
* revision: HEAD

Additionally, the meta-multimedia and meta-oe layers of the meta-openembedded repo at
git://git.openembedded.org/meta-openembedded are necessary for optional plugins
(see below).


Package names and coexistence with 0.10
---------------------------------------

Since 1.0 was designed to be able to coexist with 0.10 in the same system, the names
of the recipes for GStreamer 1.0 reflect that. The names are:

* gstreamer1.0
* gstreamer1.0-plugins-base
* gstreamer1.0-plugins-good
* gstreamer1.0-plugins-bad
* gstreamer1.0-plugins-ugly
* gstreamer1.0-libav
* gstreamer1.0-omx
* gstreamer1.0-rtsp-server


Building git versions
---------------------

By default, release tarballs are unpacked and built. It is possible, however, to build from the GStreamer
git repositories instead. It is generally better to use the releases, however for bleeding edge features
the git versions might be necessary.

To let Bitbake know that the git version of a package is preferred, add to local.conf:

    PREFERRED_VERSION_<packagename> = "git"

For example, to use git versions of all packages, add to local.conf:

    PREFERRED_VERSION_gstreamer1.0 = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-base = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-good = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-bad = "git"
    PREFERRED_VERSION_gstreamer1.0-plugins-ugly = "git"
    PREFERRED_VERSION_gstreamer1.0-libav = "git"
    PREFERRED_VERSION_gstreamer1.0-omx = "git"


Enabling plugins with dependencies
----------------------------------

By default, in the base/good/bad/ugly recipes, only dependency-less plugins and plugins with dependencies
that are supported by OE-core (i.e. recipes for them exist in OE-core) are always enabled.

These are:
* gstreamer1.0-plugins-base : gio-unix-2.0, ogg, pango, theora, vorbis
* gstreamer1.0-plugins-good : cairo, flac, gdk-pixbuf, gudev, jpeg, libpng, soup, speex, taglib, v4l2
* gstreamer1.0-plugins-bad : bz2, curl, dash, dtls, hls, neon, rsvg, sbc, smoothstreaming, sndfile, uvch264, webp
* gstreamer1.0-plugins-ugly : a52dec, lame, mpg123, mpeg2dec
* gstreamer1.0-libav : yasm

With the X11, Wayland, ALSA, BlueZ, DirectFB, OpenGL, and PulseAudio plugins, the situation is a bit different.
They are built depending on the contents of the DISTRO\_FEATURES value (in other words, depending on what the OE
distribution supports).

The rest is disabled by default, and can be enabled by appending to the respective PACKAGECONFIG values.
For example, to enable vpx and wavpack support in gstreamer1.0-plugins-good , add to local.conf:

    PACKAGECONFIG_append_pn-gstreamer1.0-plugins-good = "vpx wavpack"

Note that after enabling a plugin this way, it must be ensured that recipes for the plugin's dependencies
are available. In the example above, recipes for vpx and wavpack must exist. This typically means that
additional OE layers must be used (often meta-oe or meta-multimedia).

This is also how Orc support is enabled internally. Since version 1.6.0, Orc is included in this layer,
and enabled by default. (Orc is present in OE-Core, but to make GStreamrer 1.6 buildable with older
layers, its recipe is included.)

Below is a list of all configuration values for enabling additional plugins and features in the packages.

* gstreamer1.0
    * `check` : build unit test libraries
    * `debug` : enable debug build
    * `tests` : build test applications
    * `valgrind` : enable run-time valgrind detection
    * `gst-tracer-hooks` : enable tracing subsystem hooks
    * `trace-historic` : enable historic tracing subsystem
* gstreamer1.0-plugins-base
    * `cdparanoia` : cdparanoia audio CD ripping plugin
    * `ivorbis`: Integer-only Vorbis decoding using the Tremor library
    * `opus` : Opus audio decoder plugin
    * `visual` : libvisual based visualization plugins
* gstreamer1.0-plugins-good
    * `dv1394' : IEEE 1394 raw video source plugins
    * `jack` : JACK audio system plugins
    * `libv4l2` : additional v4l2 plugins based on libv4l2
    * `vpx` : plugins for en- and decoding VP8 video streams, using Google's libvpx
    * `wavpack` : WavPack plugins
* gstreamer1.0-plugins-bad
    * `assrender` : ASS/SSA subtitle renderer plugins
    * `dc1394` : libdc1394 based video source plugin for IIDC cameras
    * `faac` : AAC encoding plugins using the FAAC library
    * `faad` : AAC decoding plugins using the FAAD library
    * `flite` : Flite speech synthesizer plugins
    * `fluidsynth` : FluidSynth plugins
    * `gtk` : GTK+3 plugins
    * `kms` : Video sink plugin using the Linux kernel mode setting API
    * `libmms` : Microsoft Multimedia Stream plugins
    * `libssh2` : Enable libssh2 support in cURL plugins
    * `modplug` : Decoder plugins for module files (MOD/S3M/XM/IT/..) using the ModPlug library
    * `openal` : OpenAL audio plugins
    * `opencv` : OpenCV image processing plugins
    * `openjpeg` : OpenJPEG-based JPEG2000 image decoder/encoder plugin
    * `opusparse` : Opus bitstream parser plugin
    * `resindvd` : DVD navigation and playback plugin
    * `rtmp` : Real Time Messaging Protocol (RTMP) plugins
    * `schroedinger` : Dirac video codec plugins using the schroedinger library
    * `srtp` : RFC 3711 SRTP plugin
    * `voaacenc` : OpenCORE based AAC encoder plugin
    * `voamrwbenc` : OpenCORE based AMR wideband encoder plugin
    * `webp` : WebP plugins
* gstreamer1.0-plugins-ugly
    * `amrnb` : OpenCORE based AMR narrowband decoder plugin
    * `amrwb` : OpenCORE based AMR wideband decoder plugin
    * `cdio` : Compact Disc audio plugins using libcdio
    * `dvdread` : DVD source plugins using libdvdread
    * `mad` : mp3 decoder plugin based on the mad library
    * `x264` : h.264/AVC encoder plugin using libx264
* gstreamer1.0-libav
    * `libav` : builds the package using the system's libav instead of the included one (*not recommended* unless you really know what you are doing!)
    * `gpl` : build the package in GPL mode (enables GPL elements)
    * `valgrind` : enable run-time valgrind detection


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

Furthermore, if a device specific .bbappend file is written, it is recommended
to also set the value of `GSTREAMER_1_0_OMX_CORE_NAME` in it. This value
specifies the filename of the OpenMAX core (a shared library) that needs to be
used. With the Bellagio OpenMAX implementation, its value is:
`${libdir}/libomxil-bellagio.so.0`. The gstreamer1.0-omx recipe needs this value
for adjusting the `core-name` entries in the `gstomx.conf` configuration file.
