require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2.1+ & LGPLv2+"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068 "

DEPENDS += "gstreamer1.0-plugins-base libid3tag"

SRC_URI[md5sum] = "8754edf6c3be235f232fb75ad11708bb"
SRC_URI[sha256sum] = "b78b8cfabe322497da432a0f297dbb21862a033f95e8d4cd8f207eccb5288f2b"

PR = "r1"

S = "${WORKDIR}/gst-plugins-ugly-${PV}"

inherit gettext


PACKAGECONFIG ??= " \
    a52dec lame mad mpeg2dec \
    "

PACKAGECONFIG[a52dec]   = "--enable-a52dec,--disable-a52dec,liba52"
PACKAGECONFIG[cdio]     = "--enable-cdio,--disable-cdio,libcdio"
PACKAGECONFIG[dvdread]  = "--enable-dvdread,--disable-dvdread,libdvdread"
PACKAGECONFIG[lame]     = "--enable-lame,--disable-lame,lame"
PACKAGECONFIG[mad]      = "--enable-mad,--disable-mad,libmad"
PACKAGECONFIG[mpeg2dec] = "--enable-mpeg2dec,--disable-mpeg2dec,mpeg2dec"
PACKAGECONFIG[x264]     = "--enable-x264,--disable-x264,x264"


EXTRA_OECONF += " \
    --disable-amrnb \
    --disable-amrwb \
    --disable-sidplay \
    --disable-twolame \
    ${GSTREAMER_1_0_ORC} \
    "

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
