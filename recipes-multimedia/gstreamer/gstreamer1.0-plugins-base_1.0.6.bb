require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=622921ffad8cb18ab906c56052788a3f \
                    file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                   "

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 libxv', '', d)}"
DEPENDS += "freetype liboil util-linux"

SRC_URI[md5sum] = "7123a7f9955bbf1a5a7bb75192c15427"
SRC_URI[sha256sum] = "85e5f99af690f720ccde5ea7e793269d35883a3ead80ca9985fa44e18bb1a4a5"

PR = "r1"

S = "${WORKDIR}/gst-plugins-base-${PV}"

inherit gettext


PACKAGECONFIG ??= " \
    ${@base_contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
    ivorbis ogg theora vorbis \
    "

X11DEPENDS = "virtual/libx11 libsm libxrender"
X11ENABLEOPTS = "--enable-x --enable-xvideo --enable-xshm"
X11DISABLEOPTS = "--disable-x --disable-xvideo --disable-xshm"
PACKAGECONFIG[x11]     = "${X11ENABLEOPTS},${X11DISABLEOPTS},${X11DEPENDS}"
PACKAGECONFIG[alsa]    = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[ivorbis] = "--enable-ivorbis,--disable-ivorbis,tremor"
PACKAGECONFIG[ogg]     = "--enable-ogg,--disable-ogg,libogg"
PACKAGECONFIG[theora]  = "--enable-theora,--disable-theora,libtheora"
PACKAGECONFIG[vorbis]  = "--enable-vorbis,--disable-vorbis,libvorbis"
PACKAGECONFIG[pango]   = "--enable-pango,--disable-pango,pango"


# cdparanoia and libvisual do not seem to exist anywhere in OE
EXTRA_OECONF += " \
    --disable-freetypetest \
    --disable-oggtest \
    --disable-vorbistest \
    --disable-cdparanoia \
    --disable-libvisual \
    ${GSTREAMER_1_0_ORC} \
"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm -f ${S}/m4/lib-link.m4
}

FILES_${PN} += "${datadir}/gst-plugins-base"
