import { Dispatch, SetStateAction } from "react";

export default function handleSectionVisibility(idx: number, sectionVisibility: boolean[], setSectionVisibility: Dispatch<SetStateAction<boolean[]>>) {
    const newSectionVisibility: boolean[] = sectionVisibility.map((section: boolean, index: number) => idx === index);
    setSectionVisibility(newSectionVisibility);
}