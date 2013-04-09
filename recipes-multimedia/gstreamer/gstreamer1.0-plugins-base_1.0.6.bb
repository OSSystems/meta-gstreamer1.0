require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=622921ffad8cb18ab906c56052788a3f \
                    file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
                   "

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 libxv', '', d)}"
DEPENDS += "alsa-lib freetype liboil libogg libvorbis libtheora util-linux tremor"

SRC_URI[md5sum] = "7123a7f9955bbf1a5a7bb75192c15427"
SRC_URI[sha256sum] = "85e5f99af690f720ccde5ea7e793269d35883a3ead80ca9985fa44e18bb1a4a5"

PR = "r1"

S = "${WORKDIR}/gst-plugins-base-${PV}"

inherit gettext

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-gnome_vfs ${GSTREAMER_1_0_ORC}"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm -f ${S}/m4/lib-link.m4
}

FILES_${PN} += "${datadir}/gst-plugins-base"
