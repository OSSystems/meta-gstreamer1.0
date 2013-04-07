require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2.1+ & LGPLv2+"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068 "

DEPENDS += "gstreamer1.0-plugins-base libid3tag libmad mpeg2dec liba52 lame"

SRC_URI[md5sum] = "9ffee39153f419ae2329fbf54ed708e0"
SRC_URI[sha256sum] = "8655ceec7533b5d30080a5051025e26ff8d06bea8d03a6b2af56c2f839d60586"

PR = "r1"

S = "${WORKDIR}/gst-plugins-ugly-${PV}"

inherit gettext

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia \
                 ${GSTREAMER_1_0_ORC}"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
