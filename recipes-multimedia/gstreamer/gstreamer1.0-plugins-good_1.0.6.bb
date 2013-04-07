require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=622921ffad8cb18ab906c56052788a3f \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

DEPENDS += "gstreamer1.0-plugins-base gconf cairo jpeg libpng zlib libid3tag flac speex libsoup-2.4"

SRC_URI[md5sum] = "52556db4a6cc83c915837f8694bd790b"
SRC_URI[sha256sum] = "67f7690a9826d9a6ab28b9af2536a6f3e833ee412bd59dd603c48fb3c6823e0d"

PR = "r1"

S = "${WORKDIR}/gst-plugins-good-${PV}"

inherit gettext gconf

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --disable-hal --without-check \
                 ${GSTREAMER_1_0_ORC} --disable-examples"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}

FILES_${PN}-gconfelements += "${sysconfdir}/gconf/schemas/gstreamer-1.0.schemas"
